package technology.steinhauer.demos.jee.auth.principles;

import java.io.Serializable;
import java.security.Principal;

/**
 * This class is used for JAAS authentication.
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public class JAASUserPrinciple implements Principal, Serializable {
    private String name;

    public JAASUserPrinciple(String name) {
        if (name == null) {
            throw new NullPointerException("Given name was null");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
