package net.ozero.security.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: Igor V.Sadovnikov (igorvsd@gmail.com igor@lionhearth.com)
 * Date: 11/26/11
 * Time: 5:32 PM
 */

@XmlRootElement(name = "auth-constraint")
@XmlAccessorType(XmlAccessType.NONE)
public class AuthConstraintJ {

    @XmlElement(name = "role")
    private String role;

    public String getRole() {
        return role;
    }
}
