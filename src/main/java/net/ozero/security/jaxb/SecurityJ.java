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
 * Time: 5:15 PM
 */

@XmlRootElement(name = "security")
@XmlAccessorType(XmlAccessType.NONE)
public class SecurityJ {

    @XmlElementRef(type = SecurityConstraintJ.class)
    private List<SecurityConstraintJ> securityConstraints = new ArrayList<SecurityConstraintJ>();

    public List<SecurityConstraintJ> getSecurityConstraints() {
        return securityConstraints;
    }
}
