package technology.steinhauer.demos.jee.auth.principles;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * TODO Add description here
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
@RunWith(Suite.class)
@Suite.SuiteClasses ({
        JAASUserPrincipalTest.class,
        JAASPasswordPrincipalTest.class,
        JAASRolePrincipalTest.class
})
public class JAASPrincipalTestSuite {

}
