package express;

import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;


public class ValidationOfFoldername {
    @Test
    public void test() {
        // Авторизация
        LoginPage loginPage = new LoginPage();
        loginPage.open()
                .enterUsername("")
                .enterPassword("")
                .clickCenter()
                .clickLoginButton();

        // Работа с главной страницей
        MainPage mainPage = new MainPage();
        mainPage.clickCreateObjectsButton()
                .selectFolderOption()

                .enterObjectsName("/")
                .verifyErrorForInvalidFolderName("Имя папки не должно содержать специальные символы: /")
                .verifySaveButtonCreateDisabled()
                .clearObjectsNameField()

                .enterObjectsName(" ")
                .verifyErrorForInvalidFolderName("Имя папки не может быть пустым")
                .verifySaveButtonCreateDisabled()
                .clearObjectsNameField()

                .enterObjectsName("..")
                .verifyErrorForInvalidFolderName("Недопустимое имя папки")
                .verifySaveButtonCreateDisabled()
                .clearObjectsNameField()

                .enterObjectsName("папка-прав")
                .verifyErrorForInvalidFolderName("Папка с таким именем уже существует")
                .verifySaveButtonCreateDisabled()
                .clearObjectsNameField()

                //Создаётся файл с 250 символами
                .enterObjectsName("001normWordForFolderName2134567890123463274327894389387hdhgiisudhiuhisdhuigsfbhsdbfsbfsfvbusabdfubuy23guyt1g2tg7eg178gh2uidbsuybsadyufbsadg2387g2138egbbfuysdfbosdbfasfasf78banubf78abf8wrb783rbnuiafb78wauibn78fwi3nfcuiawhf82131212331212321321321213321")
                .saveObjects()
                .findAndRightClickOnFolder(" 001normWordForFolderName2134567890123463274327894389387hdhgiisudhiuhisdhuigsfbhsdbfsbfsfvbusabdfubuy23guyt1g2tg7eg178gh2uidbsuybsadyufbsadg2387g2138egbbfuysdfbosdbfasfasf78banubf78abf8wrb783rbnuiafb78wauibn78fwi3nfcuiawhf82131212331212321321321213321 ")
                .clickRename()
                .renameObjects("001normWordForFolderName2134567890123463274327894389387hdhgiisudhiuhisdhuigsfbhsdbfsbfsfvbusabdfubuy23guyt1g2tg7eg178gh2uidbsuybsadyufbsadg2387g2138egbbfuysdfbosdbfasfasf78banubf78abf8wrb783rbnuiafb78wauibn78fwi3nfcuiawhf82131212331212321321321213321/")
                .verifyErrorForInvalidObjectsRename("Имя папки не должно содержать специальные символы: /")
                .verifySaveButtonSaveDisabled()

                .renameObjects(" ")
                .verifyErrorForInvalidObjectsRename("Имя папки не может быть пустым")
                .verifySaveButtonSaveDisabled()

                .renameObjects("..")
                .verifyErrorForInvalidObjectsRename("Недопустимое имя папки")
                .verifySaveButtonSaveDisabled()

                .renameObjects("папка-прав")
                .verifyErrorForInvalidObjectsRename("Папка с таким именем уже существует")
                .verifySaveButtonSaveDisabled()

                .renameObjects(" папка-прав1 ")
                .renameSaveObjects();

        System.out.println("done");
    }

}


