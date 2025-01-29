package express;

import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;





public class ValidationOfFileNameTests {
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
                .selectDocOption()

                .enterObjectsName("/")
                .verifyErrorForInvalidFolderName("Имя файла не должно содержать специальные символы: /")
                .verifySaveButtonCreateDisabled()

                //Имя файла может быть пустым - надо завести баг. Если там просто стереть, а не натыкать пробелов, то ошибка появляется, но не по кейсу
//                .enterFolderName(" ")
//                .verifyErrorForInvalidFolderName("Недопустимое имя файла")
//                .verifySaveButtonCreateDisabled()

                .enterObjectsName("..")
                .verifyErrorForInvalidFolderName("Недопустимое имя файла")
                .verifySaveButtonCreateDisabled()

                .enterObjectsName("Документ1")
                .verifyErrorForInvalidFolderName("Файл с таким именем уже существует")
                .verifySaveButtonCreateDisabled()

                //Получилось создать с 240 символами
                .enterObjectsName("008normWordForFolderName2134567890123463274327894389387hdhgiisudhiuhisdhuigsfbhsdbfsbfsfvbusabdfubuy23guyt1g2tg7eg178gh2uidbsuybsadyufbsadg2387g2138egbbfuysdfbosdbfasfasf78banubf78abf8wrb783rbnuiafb78wauibn78fwi3nfcuiawhf8213121233121232132")
                .saveObjects()
                .goBackToMainPage()
                .findAndRightClickOnDoc(" 008normWordForFolderName2134567890123463274327894389387hdhgiisudhiuhisdhuigsfbhsdbfsbfsfvbusabdfubuy23guyt1g2tg7eg178gh2uidbsuybsadyufbsadg2387g2138egbbfuysdfbosdbfasfasf78banubf78abf8wrb783rbnuiafb78wauibn78fwi3nfcuiawhf8213121233121232132.docx ")
                .clickRename()
                .renameObjects("008normWordForFolderName2134567890123463274327894389387hdhgiisudhiuhisdhuigsfbhsdbfsbfsfvbusabdfubuy23guyt1g2tg7eg178gh2uidbsuybsadyufbsadg2387g2138egbbfuysdfbosdbfasfasf78banubf78abf8wrb783rbnuiafb78wauibn78fwi3nfcuiawhf8213121233121232132/.docx")
                .verifyErrorForInvalidObjectsRename("Имя файла не должно содержать специальные символы: /")
                .verifySaveButtonSaveDisabled()

//                .renameFolder(" ")
//                .verifyErrorForInvalidFolderRename("Имя файла не может быть пустым")
//                .verifySaveButtonSaveDisabled()
//                .clearFolderNameField()

                .renameObjects("..")
                .verifyErrorForInvalidObjectsRename("Недопустимое имя файла")
                .verifySaveButtonSaveDisabled()

                .renameObjects("Документ1.docx")
                .verifyErrorForInvalidObjectsRename("Файл с таким именем уже существует")
                .verifySaveButtonSaveDisabled()

                .renameObjects(" Документ1.docx ")
                .renameSaveObjects();

        System.out.println("done");

    }

}
