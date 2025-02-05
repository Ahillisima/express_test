import org.junit.Test;

public class LoginTest extends BaseSeleniumTest{
    @Test
    public void test() {
        // Авторизация
        loginPage.open()
                .enterUsername("")
                .enterPassword("")
                .clickLoginButton();
    }
}
