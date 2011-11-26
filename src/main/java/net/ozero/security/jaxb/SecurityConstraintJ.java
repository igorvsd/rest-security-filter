package net.ozero.security.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: Igor V.Sadovnikov (igorvsd@gmail.com igor@lionhearth.com)
 * Date: 11/26/11
 * Time: 5:16 PM
 */

@XmlRootElement(name = "security-constraint")
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityConstraintJ {

    @XmlElementRef(type = WebResourceCollectionJ.class)
    private List<WebResourceCollectionJ> webResources = new ArrayList<WebResourceCollectionJ>();

    @XmlElementRef(type = AuthConstraintJ.class)
    private AuthConstraintJ authConstraint;

    public List<WebResourceCollectionJ> getWebResources() {
        return webResources;
    }

    public AuthConstraintJ getAuthConstraint() {
        return authConstraint;
    }
}
