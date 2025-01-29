package tests;

import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;





public class TestLogin {
    @Test
    public void test() {
        // Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.open()
                .enterUsername("")
                .enterPassword("")
                .clickCenter()
                .clickLoginButton();



        System.out.println("done");
    }


}
