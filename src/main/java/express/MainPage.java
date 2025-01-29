package pages;

import com.codeborne.selenide.*;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;

import java.sql.SQLOutput;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public MainPage clickCreateObjectsButton() {
        $x("//*[@id=\"app-content-vue\"]/div[2]/div/div/div/div/button")
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .click();
        return this;
    }

    public MainPage selectFolderOption() {
        $x("//ul/li[1]/button/span[contains(text(), 'Папка')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .click();
        return this;
    }
    public MainPage selectDocOption() {
        $x("//div[2]/div[1]/div[1]/ul/li[2]/button/span[contains(text(), 'Документ')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(5))
                .click();
        return this;
    }

    public MainPage enterObjectsName(String objectsName) {
        SelenideElement inputFieldObjects =  $x("//*[@id=\"create-node-input\"]");

        inputFieldObjects.shouldBe(Condition.visible).click();
        inputFieldObjects.setValue(objectsName);

        return this;
    }

    public MainPage renameObjects(String renameFolder){
        SelenideElement renameFieldFolder = $x("//*[@id=\"rename-node-input\"]");

        renameFieldFolder.shouldBe(Condition.visible).click();
        renameFieldFolder.setValue(renameFolder);

        return this;
    }

    public MainPage clearObjectsNameField() {
        SelenideElement clearInputField = $x("//*[@id=\"create-node-input\"]");
        clearInputField.shouldBe(Condition.visible).clear();
        return this;
    }
    //Тут делали, надо передалть по такому же принцпу.
    public MainPage verifyErrorForInvalidFolderName(String expectedMessage) {
        SelenideElement errorMessage = $x("//*[@id='create-node-input-message']")
                .shouldBe(Condition.visible).shouldHave(Condition.text(expectedMessage));
        return this;
    }

    public MainPage verifyErrorForInvalidObjectsRename(String expectedMessage) {
        SelenideElement errorRenameMessage = $x("//*[@id=\"rename-node-input-message\"]")
                .shouldBe(Condition.visible);
        String actualRenameMessage = errorRenameMessage.getText();

        if (actualRenameMessage.equals(expectedMessage)) {
            System.out.println("Сообщение видно");
        } else {
            throw new IllegalStateException("Сообщения не видно");
        }

        return this;
    }

    public MainPage verifySaveButtonCreateDisabled() {
        SelenideElement saveButton = $x("//button[@type='submit' and contains(@class, 'ed-button')]");
        // Проверить, содержит ли класс "ed-button_disabled"
        if (saveButton.getAttribute("class").contains("ed-button_disabled")) {
            System.out.println("Кнопка 'Создать' задизейблена.");
        } else {
            throw new IllegalStateException("Кнопка 'Создать' активна, хотя должна быть задизейблена.");
        }
        return this;
    }

    public MainPage verifySaveButtonSaveDisabled() {
        SelenideElement saveSaveButton = $x("//button[@type='submit' and contains(@class, 'ed-button')]");

        // Проверяем, содержит ли класс "ed-button_disabled"
        if (saveSaveButton.getAttribute("class").contains("ed-button_disabled")) {
            System.out.println("Кнопка 'Сохранить' задизейблена.");
        } else {
            throw new IllegalStateException("Кнопка 'Сохранить' активна, хотя должна быть задизейблена.");
        }

        return this;
    }

    public MainPage saveObjects() {
        $x("//div/div/div[2]/button[2]/span[contains(text(), 'Создать')]")
                .shouldBe(Condition.visible).click();
        return this;
    }

    public MainPage renameSaveObjects() {
        $x("//div/div/div[2]/button[2]/span[contains(text(), 'Сохранить')]")
                .shouldBe(Condition.visible, Duration.ofSeconds(5)).click();

        return this;
    }
    // Переделать нижний так же 127
    public MainPage findAndRightClickOnFolder(String objectsName) {
           $$x("//*[@id='app-content-vue']/div[4]/div/table/div/tbody/tr/td[2]/span[2]/a/span")
                   .stream().filter(a->a.getText().equals(objectsName)).findFirst().get().shouldBe(Condition.visible, Duration.ofSeconds(5)).contextClick();
        //*[@id="app-content-vue"]/div[4]/div/table/div/tbody/tr[6]/td[2]/span[2]/a/span/span
        Configuration.timeout = 5000;
        return this;
    }

    public MainPage findAndRightClickOnDoc(String folderName) {
        ElementsCollection elementsDoc = $$x("//*[@id=\"app-content-vue\"]/div[4]/div/table/div/tbody/tr/td[2]/span[2]/a/span/span");

        for (SelenideElement element : elementsDoc) {
            if (element.getText().equals(folderName)) {
                System.out.println("Найден созданный файл: " + folderName);
                element.shouldBe(Condition.visible, Duration.ofSeconds(5)).contextClick();

                return this;
            }

        }
        throw new IllegalStateException("Папка с именем " + folderName + " не найдена.");
    }

    public MainPage clickRename() {
                $x("//li/button/span[contains(text(), 'Переименовать')]")
                .shouldBe(Condition.visible)
                .click();

                return this;
    }

    public MainPage goBackToMainPage() {
        SelenideElement goBack = $x("//div[5]/div[1]/div");
        goBack.shouldBe(Condition.visible, Duration.ofSeconds(5));
        goBack.click();

        return this;
    }

//    public MainPage deleteFolder() {
//        //Ищем элемент с текстом "Удалить" и кликаем по нему
//        $x("//li/button/span[contains(text(), 'Удалить')]")
//                .shouldBe(Condition.visible)
//                .click();
//
//        $x("//div/div/button/span[contains(text(), 'Удалить')]")
//                .shouldBe(Condition.visible)
//                .click();
//
//        return this;
//    }
    public MainPage addTags(String tagName) {
        ElementsCollection files = $$x("//*[@id='app-content-vue']/div[4]/div/table/div/tbody/tr/td[2]/span[2]/a/span");
        if (files.isEmpty()) {
            System.out.println("Файлы не найдены");
        } else {
            System.out.println("Найдены файлы:" + files.size());
            for (SelenideElement file : files) {
                System.out.println("Файл: " + file.getText());
                file.shouldHave(Condition.visible).contextClick();


                SelenideElement infoFileOption = $x("//li/button/span[contains(text(), 'Информация о файле')]");
                SelenideElement infoFolderOption = $x("//li/button/span[contains(text(), 'Информация о папке')]");

                if (infoFileOption.exists() && infoFileOption.isDisplayed()) {
                    System.out.println("Выбран пункт - инфо о файле");
                    infoFileOption.shouldHave(Condition.visible).click();

                } else if (infoFolderOption.exists() && infoFolderOption.isDisplayed()) {
                    System.out.println("Выбран пункт - инфо о папке");
                    infoFolderOption.shouldHave(Condition.visible).click();
                } else {
                        throw new IllegalStateException("Ничо не найдено");
                }
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                executeJavaScript("document.querySelector('#tab-sharing > div > div > div.tags > div > div.tags__tags-list-open-button-wrapper > button > span.ed-button__text > span').click();");

                SelenideElement confirmTag = $x("//*[@id=\"search-tag\"]");
                confirmTag.shouldHave(Condition.visible).setValue(tagName).pressEnter();
            }
        }
        return  this;
    }
}
