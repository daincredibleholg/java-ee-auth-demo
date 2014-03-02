package technology.steinhauer.demos.jee.auth.principles;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * TODO Add description here
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public class JAASUserPrincipleTest {

    @Test
    public void nameIsSuccessfullySet () throws Exception {
        JAASUserPrinciple principle = new JAASUserPrinciple("testus.test");
        assertEquals("testus.test", principle.getName());
    }

    @Test
    public void anotherNameTest() throws Exception {
        JAASUserPrinciple principle = new JAASUserPrinciple("foo.bar");
        assertEquals("foo.bar", principle.getName());
    }

    @Test (expected = NullPointerException.class)
    public void checkErrorHandlingForEmptyName() throws Exception {
        JAASUserPrinciple principle = new JAASUserPrinciple(null);
        fail("Instanciation with empty / null name should throw an exception.");
    }
}
