package net.ozero.security.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * User: Igor V.Sadovnikov (igorvsd@gmail.com igor@lionhearth.com)
 * Date: 11/26/11
 * Time: 4:48 PM
 */
public class JAXBContextWrapper {

    private static final Class[] JAXB_CLASSES = {
            SecurityJ.class,
            SecurityConstraintJ.class,
            WebResourceCollectionJ.class,
            AuthConstraintJ.class
    };

    public static Object unmarshalSecurityConfig(InputStream aInputStream) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(JAXB_CLASSES);
        return context.createUnmarshaller().unmarshal(aInputStream);
    }

}
