package pages;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private static final String BUTTON_PLUS_SELECTOR = ".ed-button_primary.ed-button_height-medium";
    private static final String FOLDER_SELECTOR = ".create-picker__menu-entry:nth-child(1)";
    private static final String DOC_FILE_SELECTOR = ".create-picker__menu-entry:nth-child(2)";
    private static final String OBJECTS_NAME_INPUT_SELECTOR = "#create-node-input";
    private static final String RENAME_OBJECTS_INPUT_SELECTOR = "#rename-node-input";
    private static final String CREATE_ERROR_MESSAGE = "#create-node-input-message";
    private static final String RENAME_ERROR_MESSAGE = "#rename-node-input-message";
    private static final String CREATE_BUTTON_SELECTOR = ".ed-button_primary";
    private static final String DIALOG_ACTIONS_SELECTOR = ".modal-container";
    private static final String DOCUMENT_LOCATOR = "#app-content-vue div table tbody tr td span.v-popper--has-tooltip";
    private static final String RENAME_BUTTON_SELECTOR = "button[data-cy-files-list-row-action=\"rename\"]";
    private static final String GO_BACK_BUTTON_SELECTOR = ".onlyoffice-editor-header__back-button";


    public MainPage clickCreateObjectsButton() {
        $(BUTTON_PLUS_SELECTOR).shouldBe(Condition.visible).click();
        return this;
    }

    public MainPage selectFolderOption() {
        $(FOLDER_SELECTOR).shouldBe(Condition.visible).click();
        return this;
    }

    public MainPage selectDocOption() {
        $(DOC_FILE_SELECTOR).shouldBe(Condition.visible).click();
        return this;
    }

    public MainPage enterObjectsName(String objectsName) {
        $(DIALOG_ACTIONS_SELECTOR).$(OBJECTS_NAME_INPUT_SELECTOR).shouldBe(Condition.visible).setValue(objectsName);
        return this;
    }

    public MainPage renameObjects(String newName) {
        $(DIALOG_ACTIONS_SELECTOR).$(RENAME_OBJECTS_INPUT_SELECTOR).shouldBe(Condition.visible).setValue(newName);
        return this;
    }

//    public MainPage clearObjectsNameField() {
//        SelenideElement clearInputField = $x("//*[@id=\"create-node-input\"]");
//        clearInputField.shouldBe(Condition.visible).clear();
//        return this;
//    }
    //Тут делали, надо передалть по такому же принцпу.
    public MainPage verifyErrorForInvalidFolderName(String expectedMessage) {
        $(DIALOG_ACTIONS_SELECTOR).$(CREATE_ERROR_MESSAGE).shouldBe(Condition.visible).shouldHave(Condition.text(expectedMessage));
        return this;
    }

    public MainPage verifyErrorForInvalidObjectsRename(String expectedMessage) {
        $(DIALOG_ACTIONS_SELECTOR).$(RENAME_ERROR_MESSAGE).shouldBe(Condition.visible).shouldHave(Condition.text(expectedMessage));
        return this;
    }

    public MainPage verifySaveButtonCreateDisabled() {
        // Проверяем, содержит ли кнопка класс "ed-button_disabled"
        $(DIALOG_ACTIONS_SELECTOR).$(CREATE_BUTTON_SELECTOR).shouldHave(Condition.cssClass("ed-button_disabled"));
        return this;
    }

    public MainPage saveObjects() {
        $(DIALOG_ACTIONS_SELECTOR).$(CREATE_BUTTON_SELECTOR).shouldBe(Condition.visible).click();
        return this;
    }

    public MainPage findAndRightClickOnDoc(String folderName) {
        $$(DOCUMENT_LOCATOR)
                .stream().filter(a->a.getText().equals(folderName)).findFirst().get().shouldBe(Condition.visible).contextClick();
        return this;
    }

    public MainPage clickRename() {
        $(RENAME_BUTTON_SELECTOR).shouldBe(Condition.visible).click();
        return this;
    }

    public MainPage goBackToMainPage() {
        $(GO_BACK_BUTTON_SELECTOR).shouldBe(Condition.visible).click();
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
