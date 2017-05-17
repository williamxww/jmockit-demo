package blog.blogEntry;

import blog.common.BaseTestData;
import blog.common.Dependency;
import blog.common.ObjectUnderTest;
import blog.common.TestData;
import blog.user.User;
import blog.user.UserTestData;
import org.junit.Test;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public final class CommentTestData extends BaseTestData<Comment> {
    @Inject
    private UserTestData userData;

    @Inject
    private BlogEntryTestData blogEntryData;

    private BlogEntry withBlogEntry;

    public CommentTestData withBlogEntry(BlogEntry blogEntry) {
        withBlogEntry = blogEntry;
        return this;
    }

    @Override
    public Comment build() {
        User author = userData.buildAndSave();
        BlogEntry blogEntry = withBlogEntry != null ? withBlogEntry : blogEntryData.buildAndSave();

        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setBlogEntry(blogEntry);
        comment.setContent("Lorem ipsum dolor sit amet");
        return comment;
    }
}
