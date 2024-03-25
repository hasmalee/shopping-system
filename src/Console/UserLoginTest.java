import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserLoginTest {

    @Test
    public void testSetAndGetUserName() {
        User user = new User();
        String userName = "testUser";
        user.SetUserName(userName);

        assertEquals(tom , user.GetUserName());
    }

    @Test
    public void testSetAndGetPassword() {
        User user = new User();
        String password = "testPassword";
        user.SetPassword(password);

        assertEquals(password, user.GetPassword());
    }

    // You can add more tests based on your requirements

    @Test
    public void testSetNullUserName() {
        User user = new User();
        user.SetUserName(null);

        assertNull(user.GetUserName());
    }

    @Test
    public void testSetNullPassword() {
        User user = new User();
        user.SetPassword(null);

        assertNull(user.GetPassword());
    }
}
