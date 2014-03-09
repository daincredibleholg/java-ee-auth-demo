package technology.steinhauer.demo.jee.server.login;

import org.apache.log4j.Logger;
import technology.steinhauer.demos.jee.auth.principles.JAASPasswordPrincipal;
import technology.steinhauer.demos.jee.auth.principles.JAASRolePrincipal;
import technology.steinhauer.demos.jee.auth.principles.JAASUserPrincipal;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TODO Add description here
 *
 * @author hsteinhauer
 * @since 02.03.14
 */
public class JAASLoginModule implements LoginModule {

    private static Logger LOGGER = Logger.getLogger(JAASLoginModule.class);

    // Initial state
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;

    // configurable option
    private boolean debug = false;

    // authentication status
    private boolean succeeded = false;
    private boolean commitSucceed = false;

    // user credentials
    private String username = null;
    private char[] password = null;

    // user principle
    private JAASUserPrincipal userPrincipal;
    private JAASPasswordPrincipal passwordPrincipal;

    public JAASLoginModule () {
        super();
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

        debug = "true".equalsIgnoreCase((String) options.get("debug"));
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Error: No CallbackHandler available");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username");
        callbacks[1] = new PasswordCallback("password: ", false);

        try {
            callbackHandler.handle(callbacks);
            username = ((NameCallback)callbacks[0]).getName();
            password = ((PasswordCallback)callbacks[1]).getPassword();

            if (debug) {
                LOGGER.debug("Username: " + username);
                LOGGER.debug("Password: " + password);
            }

            if (username == null || password == null) {
                String error = "Callback handler does not return login data properly";
                LOGGER.error(error);
                throw new LoginException(error);
            }

            if (isValidUser()) {
                succeeded = true;
                return true;
            }
        } catch (IOException | UnsupportedCallbackException e) {
            LOGGER.error("Exception caught during login process.", e);
        }

        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        if (succeeded == false) {
            return false;
        } else {
            userPrincipal = new JAASUserPrincipal(username);
            passwordPrincipal = new JAASPasswordPrincipal(new String(password));

            if (!subject.getPrincipals().contains(passwordPrincipal)) {
                subject.getPrincipals().add(passwordPrincipal);
                LOGGER.debug("Password principal added." + passwordPrincipal);
            }

            List<String> roles = getRoles();
            for (String role : roles) {
                JAASRolePrincipal rolePrincipal = new JAASRolePrincipal(role);
                if (!subject.getPrincipals().contains(rolePrincipal)) {
                    subject.getPrincipals().add(rolePrincipal);
                    LOGGER.debug("Role principal added: " + rolePrincipal);
                }
            }

            commitSucceed = true;

            LOGGER.info("Login subject were successfully populated with principals and roles.");

            return true;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        if (succeeded == false) {
            return false;
        } else if (succeeded == true && commitSucceed == false) {
            succeeded = false;
            username = null;
            password = null;
            userPrincipal = null;
        } else {
            logout();
        }

        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);

        succeeded = false;
        succeeded = commitSucceed;
        username = null;

        if (password != null) {
            for (int i = 0; i < password.length; i++) {
                password[i] = ' ';
            }
            password = null;
        }
        userPrincipal = null;
        return true;
    }

    private boolean isValidUser () throws LoginException {
        String sql = (String) options.get("userQuery");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            stmt = con.prepareStatement(sql);

        } catch (Exception e) {
            LOGGER.error("Error when loading user from the database ", e);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Error when closing result set.", e);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Error when closing statement", e);
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Error when closing connection", e);
            }
        }

        return false;
    }

    private List<String> getRoles() {

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        List<String> roleList = new ArrayList<>();

        try {
            con = getConnection();
            String sql = (String) options.get("roleQuery");
            stmt = con.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            while (rs.next()) {
                roleList.add(rs.getString("rolename"));
            }
        } catch (Exception e) {
            LOGGER.error("Error when loading roles from database", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Error when closing result set.", e);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Error when closing statement", e);
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Error when closing connection", e);
            }
        }
        return roleList;
    }

    private Connection getConnection() throws LoginException {
        String dbUser = (String) options.get("dbUser");
        String dbPassword = (String) options.get("dbPassword");
        String dbUrl = (String) options.get("dbUrl");
        String dbDriver = (String) options.get("dbDriver");

        Connection con = null;
        try {
            Class.forName(dbDriver).newInstance();
            con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Exception e) {
            LOGGER.error("Error when creating database connection", e);
        }
        return con;



    }
}
