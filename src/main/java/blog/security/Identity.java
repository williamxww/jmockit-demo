package blog.security;


import blog.user.User;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class Identity implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean loggedIn;

    @Produces
    @CurrentUser
    @Named
    private User currentUser;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getUser() {
        return currentUser;
    }

    public void setUser(User user) {
        currentUser = user;
    }
}
