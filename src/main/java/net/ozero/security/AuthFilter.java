package net.ozero.security;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import net.ozero.security.jaxb.JAXBContextWrapper;
import net.ozero.security.jaxb.SecurityConstraintJ;
import net.ozero.security.jaxb.SecurityJ;
import net.ozero.security.jaxb.WebResourceCollectionJ;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Vim.
 * User: andreychernyshev
 * Date: 11/26/11
 * Time: 4:24 AM
 */
public abstract class AuthFilter implements Filter {

    private static final String GUEST = "GUEST";
    private SecurityJ securityConfig = new SecurityJ();


    public abstract boolean authUser(String login, String password);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("/WEB-INF/classes/security.xml");
        try {
            securityConfig = (SecurityJ) new JAXBContextWrapper().unmarshalSecurityConfig(is);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String path = httpRequest.getPathInfo();
        String method = httpRequest.getMethod();

        String authRole = GUEST;
        for (SecurityConstraintJ s : securityConfig.getSecurityConstraints()) {
            for (WebResourceCollectionJ w : s.getWebResources()) {
                for (String url : w.getUrlPatterns()) {
                    if (
                            (
                                    (url.endsWith("*") && url.startsWith(path)) ||
                                            (!url.endsWith("*") && url.equals(path))
                            ) && w.getHttpMethods().contains(method)
                            ) {
                        authRole = s.getAuthConstraint().getRole();
                    }
                }
            }
        }

        if (authRole.equals(GUEST)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        final String auth = httpRequest.getHeader("Authorization");
        if (auth != null) {

            final int index = auth.indexOf(' ');
            if (index > 0) {
                final String[] credentials = StringUtils.split(new String(Base64.decodeBase64(auth.substring(index).getBytes("UTF-8"))), ':');
                if (credentials.length == 2) {
                    boolean hasAccess = authUser(credentials[0], credentials[1]);
                    if (hasAccess) {
                        filterChain.doFilter(httpRequest, httpResponse);
                        return;
                    }
                }
            }
        }

        httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    public void destroy() {
        securityConfig = null;
    }
}
