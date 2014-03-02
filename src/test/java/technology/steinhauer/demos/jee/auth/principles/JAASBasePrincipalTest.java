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
public class JAASBasePrincipalTest {
    JAASBasePrincipal principle;

    @Test
    public void nameIsSuccessfullySet () throws Exception {
        assertEquals("testus.test", principle.getName());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Principle [name = testus.test]", principle.toString());
    }
}
