package blog.user;

import blog.common.ObjectUnderTest;
import blog.common.TestData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public final class UserServiceTest {
    @TestData
    UserTestData userData;

    @ObjectUnderTest
    UserService userService;

    @Test
    public void findExistingByUsername() {
        User user = userData.buildAndSave();

        User foundByUsername = userService.findByUsername(user.getUsername());

        assertEquals(user.getId(), foundByUsername.getId());
    }

    @Test
    public void findNonExistingByUsername() {
        User user = userService.findByUsername("name");

        assertNull(user);
    }
}
