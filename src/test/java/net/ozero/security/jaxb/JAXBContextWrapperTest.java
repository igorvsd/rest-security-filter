package net.ozero.security.jaxb;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

/**
 * User: Igor V.Sadovnikov (igorvsd@gmail.com igor@lionhearth.com)
 * Date: 11/26/11
 * Time: 5:23 PM
 */
public class JAXBContextWrapperTest {

    private JAXBContextWrapper wrapper;

    @Before
    public void setUp() {
        // setup
        wrapper = new JAXBContextWrapper();
    }

    @Test
    public void testUnmarshalSecurityConfig() throws JAXBException {
        // setup


        // act
        SecurityJ result = (SecurityJ) wrapper.unmarshalSecurityConfig(getClass().getResourceAsStream("security.xml"));

        // verify
        assertThat(result.getSecurityConstraints().size(), is(2));
        assertThat(result.getSecurityConstraints().get(0).getAuthConstraint().getRole(), is("USER"));
        assertThat(result.getSecurityConstraints().get(0).getWebResources().size(), is(1));
        assertThat(result.getSecurityConstraints().get(0).getWebResources().get(0).getHttpMethods().size(), is(2));
        assertThat(result.getSecurityConstraints().get(0).getWebResources().get(0).getHttpMethods().get(0), is("GET"));
        assertThat(result.getSecurityConstraints().get(0).getWebResources().get(0).getHttpMethods().get(1), is("POST"));
        assertThat(result.getSecurityConstraints().get(0).getWebResources().get(0).getUrlPatterns().size(), is(1));
        assertThat(result.getSecurityConstraints().get(0).getWebResources().get(0).getUrlPatterns().get(0), is("/*"));

        assertThat(result.getSecurityConstraints().get(1).getAuthConstraint().getRole(), is("GUEST"));
        assertThat(result.getSecurityConstraints().get(1).getWebResources().size(), is(1));
        assertThat(result.getSecurityConstraints().get(1).getWebResources().get(0).getHttpMethods().size(), is(1));
        assertThat(result.getSecurityConstraints().get(1).getWebResources().get(0).getHttpMethods().get(0), is("POST"));
        assertThat(result.getSecurityConstraints().get(1).getWebResources().get(0).getUrlPatterns().size(), is(1));
        assertThat(result.getSecurityConstraints().get(1).getWebResources().get(0).getUrlPatterns().get(0), is("/login"));

    }
}
