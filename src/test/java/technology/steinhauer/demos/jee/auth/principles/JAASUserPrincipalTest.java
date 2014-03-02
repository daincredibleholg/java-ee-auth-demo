package technology.steinhauer.demos.jee.auth.principles;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * TODO Add description here
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public class JAASUserPrincipalTest {
    private JAASUserPrincipal principle;

    @Before
    public void setUp() throws Exception {
        principle = new JAASUserPrincipal("testus.test");
    }

    @Test
    public void nameIsSuccessfullySet () throws Exception {
        assertEquals("testus.test", principle.getName());
    }

    @Test (expected = NullPointerException.class)
    public void checkErrorHandlingForEmptyName() throws Exception {
        JAASUserPrincipal principle = new JAASUserPrincipal(null);
        fail("Instanciation with empty / null name should throw an exception.");
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("UserPrinciple [name = testus.test]", principle.toString());
    }
}
