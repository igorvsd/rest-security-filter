package net.ozero.security.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: Igor V.Sadovnikov (igorvsd@gmail.com igor@lionhearth.com)
 * Date: 11/26/11
 * Time: 5:17 PM
 */

@XmlRootElement(name = "web-resource-collection")
public class WebResourceCollectionJ {

    @XmlElement(name = "url-pattern")
    private List<String> urlPatterns = new ArrayList<String>();

    @XmlElement(name = "http-method")
    private List<String> httpMethods = new ArrayList<String>();

    public List<String> getUrlPatterns() {
        return urlPatterns;
    }

    public List<String> getHttpMethods() {
        return httpMethods;
    }
}
