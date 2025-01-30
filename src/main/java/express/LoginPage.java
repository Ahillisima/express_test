package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    public LoginPage open() {
        Selenide.open("https://qa-prerelease.dev.disk.ccsteam.ru/login");
        return this;
    }

    public LoginPage enterUsername(String username) {
        executeJavaScript("document.querySelector('#user').value = '" + username + "';");
        return this;
    }

    public LoginPage enterPassword(String password) {
        executeJavaScript("document.querySelector('#password').value = '" + password + "';");
        return this;
    }

    public LoginPage clickCenter() {
        executeJavaScript(
                "var event = new MouseEvent('click', {" +
                        "    bubbles: true," +
                        "    cancelable: true," +
                        "    view: window," +
                        "    clientX: window.innerWidth / 2," +
                        "    clientY: window.innerHeight / 2" +
                        "});" +
                        "document.elementFromPoint(window.innerWidth / 2, window.innerHeight / 2).dispatchEvent(event);"
        );
        return this;
    }

    public pages.MainPage clickLoginButton() {
        executeJavaScript("document.querySelector('#body-login > main > div.login-box > form > div > button.ed-button.ed-button_primary.ed-button_block.ed-button_height-medium').click();");
        return new pages.MainPage();
    }

}
