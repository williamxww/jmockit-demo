package blog.blogEntry;

import blog.common.Database;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class BlogEntryDaoBean implements BlogEntryDao {
    @Inject
    private Database db;

    @Override
    public List<BlogEntry> find(int maxResults, int firstResult) {
        return db.findWithPaging("select b from BlogEntry b order by b.created desc", maxResults, firstResult);
    }

    @Override
    public Long count() {
        return db.findSingle("select count(b) from BlogEntry b");
    }
}
