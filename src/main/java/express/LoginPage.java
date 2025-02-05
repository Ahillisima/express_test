package pages;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class LoginPage {

    private static final String USERNAME_INPUT_SELECTOR = "#user";
    private static final String PASSWORD_INPUT_SELECTOR = "#password";
    private static final String LOGIN_BUTTON_SELECTOR = "#body-login > main > div.login-box > form > div > button.ed-button.ed-button_primary.ed-button_block.ed-button_height-medium";

    public LoginPage open() {
        Selenide.open("https://qa-prerelease.dev.disk.ccsteam.ru/login");
        return this;
    }

    public LoginPage enterUsername(String username) {
        executeJavaScript("document.querySelector('" + USERNAME_INPUT_SELECTOR + "').value = '" + username + "';");
        return this;
    }

    public LoginPage enterPassword(String password) {
        executeJavaScript("document.querySelector('" + PASSWORD_INPUT_SELECTOR + "').value = '" + password + "';");
        return this;
    }

    public pages.MainPage clickLoginButton() {
        executeJavaScript("document.querySelector('" + LOGIN_BUTTON_SELECTOR + "').click();");
        return new pages.MainPage();
    }
}
