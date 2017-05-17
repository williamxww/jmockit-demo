package blog.user;

import blog.common.Database;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
    @Inject
    private Database db;

    public User findByUsername(String username) {
        return db.findSingle("select u from User u where u.username = ?1", username);
    }
}
