import express.Assets;
import org.junit.Test;

public class ValidationOfFileNameTests extends BaseSeleniumTest {
    @Test
    public void test() {
        // Генерация строковых данных
        String generatedName = Assets.generateString240();
        // Авторизация
        loginPage.open()
                .enterUsername("")
                .enterPassword("")
//                .clickCenter()
                .clickLoginButton();

        // Работа с главной страницей
        mainPage.clickCreateObjectsButton()
                .selectDocOption()

                .enterObjectsName(Assets.ValidationMessage.INVALID_CHARACTER.getMessage())
                .verifyErrorForInvalidFolderName(Assets.ValidationMessage.FILE_NAME_ERROR.getMessage())
                .verifySaveButtonCreateDisabled()

                .enterObjectsName(Assets.ValidationMessage.DOT_FILE_ERROR.getMessage())
                .verifyErrorForInvalidFolderName(Assets.ValidationMessage.INVALID_FILE_NAME.getMessage())
                .verifySaveButtonCreateDisabled()

                .enterObjectsName("Документ1")
                .verifyErrorForInvalidFolderName(Assets.ValidationMessage.DUPLICATE_FILE_ERROR.getMessage())
                .verifySaveButtonCreateDisabled()

                // Успешное создание с 240 символами
                .enterObjectsName(generatedName)
                .saveObjects()
                .goBackToMainPage()
                .findAndRightClickOnDoc(" " + generatedName + Assets.FileType.DOCX.toString() + " ")
                .clickRename()

                .renameObjects(generatedName + Assets.ValidationMessage.INVALID_CHARACTER.getMessage() + Assets.FileType.DOCX.toString())
                .verifyErrorForInvalidObjectsRename(Assets.ValidationMessage.FILE_NAME_ERROR.getMessage())
                .verifySaveButtonCreateDisabled()

                .renameObjects(Assets.ValidationMessage.DOT_FILE_ERROR.getMessage())
                .verifyErrorForInvalidObjectsRename(Assets.ValidationMessage.INVALID_FILE_NAME.getMessage())
                .verifySaveButtonCreateDisabled()

                .renameObjects("Документ1" + Assets.FileType.DOCX.toString())
                .verifyErrorForInvalidObjectsRename(Assets.ValidationMessage.DOT_FILE_ERROR.getMessage())
                .verifySaveButtonCreateDisabled()

                .renameObjects(" Документ1" + Assets.FileType.DOCX.toString() + " ")
                .saveObjects();
    }
}
