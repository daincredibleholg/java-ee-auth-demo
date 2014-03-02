package technology.steinhauer.demos.jee.auth.principles;

/**
 * TODO Add description here
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public class JAASRolePrincipal extends JAASBasePrincipal {
    public JAASRolePrincipal(String name) {
        checkNameForNull(name);
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
        JAASRolePrincipal other = (JAASRolePrincipal) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;

        return true;
    }
}
