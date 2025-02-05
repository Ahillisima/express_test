import express.Assets;
import org.junit.Test;

public class ValidationOfFoldername extends BaseSeleniumTest {
    @Test
    public void test() {
        String generatedName = Assets.generateString250();
        // Авторизация
        loginPage.open()
                .enterUsername("")
                .enterPassword("")
                .clickLoginButton();
        // Работа с главной страницей
        mainPage.clickCreateObjectsButton()
                .selectFolderOption()

                .enterObjectsName(Assets.ValidationMessage.INVALID_CHARACTER.getMessage())
                .verifyErrorForInvalidFolderName(Assets.ValidationMessage.FILE_NAME_ERROR.getMessage())
                .verifySaveButtonCreateDisabled()

                .enterObjectsName(Assets.ValidationMessage.DOT_FILE_ERROR.getMessage())
                .verifyErrorForInvalidFolderName(Assets.ValidationMessage.INVALID_FILE_NAME.getMessage())
                .verifySaveButtonCreateDisabled()

                .enterObjectsName("папка-прав")
                .verifyErrorForInvalidFolderName(Assets.ValidationMessage.DUPLICATE_FILE_ERROR.getMessage())
                .verifySaveButtonCreateDisabled()

                //Создаётся файл с 250 символами
                .enterObjectsName(Assets.generateString250())
                .saveObjects()
                .findAndRightClickOnDoc(" " + generatedName + " ")
                .clickRename()

                .renameObjects(generatedName + Assets.ValidationMessage.INVALID_CHARACTER.getMessage())
                .verifyErrorForInvalidObjectsRename(Assets.ValidationMessage.FILE_NAME_ERROR.getMessage())
                .verifySaveButtonCreateDisabled()


                .renameObjects(Assets.ValidationMessage.DOT_FILE_ERROR.getMessage())
                .verifyErrorForInvalidObjectsRename(Assets.ValidationMessage.DOT_FILE_ERROR.getMessage())
                .verifySaveButtonCreateDisabled()

                .renameObjects("папка-прав")
                .verifyErrorForInvalidObjectsRename("Папка с таким именем уже существует")
                .verifySaveButtonCreateDisabled()

                .renameObjects(" папка-прав1 ")
                .saveObjects();
    }

}


