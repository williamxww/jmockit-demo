package blog.security;

import blog.user.User;
import blog.user.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Named
@RequestScoped
public class Authenticator implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;

    @Inject
    private HttpSession session;

    @Inject
    private Identity identity;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() {
        User user = userService.findByUsername(username);

        if (user != null && user.verifyPassword(password)) {
            identity.setLoggedIn(true);
            identity.setUser(user);
            return true;
        }

        return false;
    }

    public void logout() {
        session.invalidate();
    }
}
