package blog.blogEntry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import blog.common.BaseEntity;
import blog.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class BlogEntry extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @Size(min = 1)
    private String title;

    @Lob
    @Column(length = 2000)
    @Size(min = 1)
    private String content;

    @NotNull
    @ManyToOne(optional = false)
    private User author;

    @NotNull
    @Temporal(TIMESTAMP)
    private final Date created = new Date();

    @OneToMany(mappedBy = "blogEntry", cascade = ALL)
    private final List<Comment> comments = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShortContent() {
        if (content != null && content.length() > 200) {
            return content.substring(0, 200) + "...";
        }

        return content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
