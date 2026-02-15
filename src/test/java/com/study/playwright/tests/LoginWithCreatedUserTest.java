package com.study.playwright.tests;
import com.microsoft.playwright.junit.UsePlaywright;
import com.study.playwright.api.UserAPIClient;
import com.study.playwright.config.HeadlessChromeOptions;
import com.study.playwright.pages.LoginPage;
import com.study.playwright.dto.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.microsoft.playwright.Page;
import static org.assertj.core.api.Assertions.assertThat;

@UsePlaywright(HeadlessChromeOptions.class)
public class LoginWithCreatedUserTest {

    @Test
    @DisplayName("Verify that can login with a registered user")
    void loginWithCorrectUser(Page page) {
        User user = User.randomUser();
        UserAPIClient userAPIClient = new UserAPIClient(page);
        userAPIClient.registerUser(user);

        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        loginPage.loginAs(user);

        assertThat(loginPage.title()).isEqualTo("My account");
    }

    @Test
    @DisplayName("Verify error when logging with a wrong password")
    void getErrorWhenLoginWithWrongPassword(Page page) {
        User user = User.randomUser();
        UserAPIClient userAPIClient = new UserAPIClient(page);
        userAPIClient.registerUser(user);

        LoginPage loginPage = new LoginPage(page);
        loginPage.open();
        loginPage.loginAs(user.withPassword("wrong-password"));

        assertThat(loginPage.loginErrorMessage()).isEqualTo("Invalid email or password");
    }
}
