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

/**
 * Created by Vim.
 * User: andreychernyshev
 * Date: 11/26/11
 * Time: 4:24 AM
 */
public abstract class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String nonAuth = filterConfig.getInitParameter("non_auth");
        InputStream is = getClass().getClassLoader().getResourceAsStream("/WEB-INF/classes/security.xml");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        final String auth = httpRequest.getHeader("Authorization");
        if (auth != null) {

            final int index = auth.indexOf(' ');
            if (index > 0) {
//                final String[] credentials = StringUtils.split(new String(Base64.decodeBase64(auth.substring(index).getBytes("UTF-8"))), ':');
//                if (credentials.length == 2) {
//                    Login login = loginRepository.findLoginByUsername(credentials[0]);
//                    if (login != null && login.getPassword().equals(credentials[1])) {
//                        filterChain.doFilter(httpRequest, httpResponse);
//                        return;
//                    }
//
//            }
            }
        }

        httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
