package blog.blogEntry;

import blog.common.Database;
import blog.security.CurrentUser;
import blog.user.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CommentService {
    @Inject
    @CurrentUser
    private User user;

    @Inject
    private BlogEntry blogEntry;

    @Inject
    private Database db;

    private Comment instance;

    public Comment getInstance() {
        if (instance == null) {
            instance = new Comment();
            instance.setAuthor(user);
            instance.setBlogEntry(blogEntry);
        }

        return instance;
    }

    public void save() {
        db.save(instance);
        instance = null;
    }
}
