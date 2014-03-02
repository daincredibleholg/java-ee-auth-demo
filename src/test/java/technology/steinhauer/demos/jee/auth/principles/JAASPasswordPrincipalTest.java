package technology.steinhauer.demos.jee.auth.principles;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;

/**
 * TODO Add description here
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public class JAASPasswordPrincipalTest extends JAASBasePrincipalTest {

    @Before
    public void setUp() throws Exception {
        principle = new JAASPasswordPrincipal("testus.test");
    }

    @Test(expected = NullPointerException.class)
    public void checkErrorHandlingForEmptyName() throws Exception {
        principle = new JAASPasswordPrincipal(null);
        fail("Instanciation with empty / null name should throw an exception.");
    }
}
