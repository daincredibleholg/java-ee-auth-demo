package technology.steinhauer.demos.jee.auth.principles;

/**
 * TODO Add description here
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public class JAASUserPrinciple {
    private String name;

    public JAASUserPrinciple(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
