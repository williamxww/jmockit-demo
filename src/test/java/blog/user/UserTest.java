package blog.user;

import blog.common.TestData;
import org.junit.Test;

import javax.persistence.PersistenceException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class UserTest {
    @TestData
    UserTestData userData;

    @Test(expected = PersistenceException.class)
    public void attemptToSaveWithDuplicateUsername() {
        userData.withUsername("username").buildAndSave();
        userData.withUsername("username").buildAndSave();
    }

    @Test
    public void verifyPassword() {
        User user = userData.buildAndSave();

        assertFalse(user.getFirstName().isEmpty());
        assertFalse(user.getSurname().isEmpty());
        assertTrue(user.verifyPassword("secret"));
        assertFalse(user.verifyPassword("other"));
    }
}
