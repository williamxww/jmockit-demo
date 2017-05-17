package blog.blogEntry;

import blog.common.ObjectUnderTest;
import blog.common.TestData;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public final class CommentServiceTest {
    @TestData
    CommentTestData commentData;

    @ObjectUnderTest
    CommentService commentService;

    @ObjectUnderTest
    BlogEntry blogEntry;

    @Test
    public void createNewPartiallyInitializedComment() {
        Comment instance = commentService.getInstance();

        assertNull(instance.getId());
        assertSame(blogEntry, instance.getBlogEntry());
        assertNotNull(instance.getAuthor());
        assertNull(instance.getContent());
        assertNotNull(instance.getCreated());
    }

    @Test
    public void saveNewComment() {
        Comment instance = commentService.getInstance();
        instance.setContent("comment");

        commentService.save();

        commentData.assertSavedToDB(instance);
    }
}
