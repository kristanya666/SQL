package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLDataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLDataHelper.deleteDatabase;


public class DatabaseTest {

    @AfterAll
    static void cleanDatabase() {
        deleteDatabase();
    }


    @Test
    void loginTest() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLDataHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
}
