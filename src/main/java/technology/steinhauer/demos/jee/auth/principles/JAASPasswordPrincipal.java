package technology.steinhauer.demos.jee.auth.principles;

/**
 * TODO Add description here
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public class JAASPasswordPrincipal extends JAASBasePrincipal {
    public JAASPasswordPrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("Given name was null");
        }

        this.name = name;
    }
}
sts 