package blog.blogEntry;

import blog.common.Database;
import blog.security.CurrentUser;
import blog.user.User;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named
// @ConversationScoped
public class BlogEntryService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log; // just for demonstration, not actually useful

    @Inject
    private Conversation conversation; // for demonstration;
                                       // @javax.faces.view.ViewScoped is better

    @Inject
    private Database db;

    @Inject
    @CurrentUser
    private User user;

    private Long id;

    private BlogEntry instance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        beginConversation();

        log.info("set blog entry id " + id);
        this.id = id;
    }

    @Produces
    public BlogEntry getInstance() {
        if (instance == null || id != null && !id.equals(instance.getId())) {
            instance = db.find(BlogEntry.class, id);
        }

        log.info("return blog entry " + instance);
        return instance;
    }

    public void newInstance() {
        beginConversation();

        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setAuthor(user);
        instance = blogEntry;
        id = null;
    }

    private void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
            log.info("beginConversation conversation with id " + conversation.getId());
        }
    }

    private void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public void save() {
        id = db.save(instance);
    }

    public void delete() {
        log.info("delete blog entry " + instance);
        db.remove(instance);
        endConversation();
    }
}
