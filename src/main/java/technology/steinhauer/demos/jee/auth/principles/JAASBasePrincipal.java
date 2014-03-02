package technology.steinhauer.demos.jee.auth.principles;

import java.io.Serializable;
import java.security.Principal;

/**
 * TODO Add description here
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public abstract class JAASBasePrincipal implements Principal, Serializable {
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }


    @Override
    public String toString() {
        return "Principle [name = " + name +  "]";
    }

    protected void checkNameForNull(String name) {
        if (name == null) {
            throw new NullPointerException("Given name was null");
        }
    }


}
