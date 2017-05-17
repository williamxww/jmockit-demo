package blog.blogEntry;

import blog.common.ObjectUnderTest;
import blog.common.TestData;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public final class CommentListServiceTest {
    @TestData
    BlogEntryTestData blogEntryData;

    @TestData
    CommentTestData commentData;

    @ObjectUnderTest
    CommentListService commentListService;

    @ObjectUnderTest
    BlogEntryService blogEntryService;

    @Test
    public void getResultListForBlogEntryWithComments() {
        BlogEntry blogEntry = blogEntryData.buildAndSave();
        Comment comment = commentData.withBlogEntry(blogEntry).buildAndSave();
        blogEntryService.setId(blogEntry.getId());

        List<Comment> comments = commentListService.getResultList();

        assertEquals(1, comments.size());
        assertSame(comments.get(0), comment);
    }

    @Test
    public void getResultListForBlogEntryHavingNoComments() {
        BlogEntry blogEntry = blogEntryData.buildAndSave();
        blogEntryService.setId(blogEntry.getId());

        List<Comment> comments = commentListService.getResultList();

        assertEquals(0, comments.size());
    }
}
