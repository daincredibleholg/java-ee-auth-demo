package technology.steinhauer.demos.jee.auth.principles;

/**
 * This class is used for JAAS authentication.
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public class JAASUserPrincipal extends JAASBasePrincipal {

    public JAASUserPrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("Given name was null");
        }

        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JAASUserPrincipal other = (JAASUserPrincipal) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;

        return true;
    }
}
