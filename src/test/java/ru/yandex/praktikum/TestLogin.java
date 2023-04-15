package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.client.UserClient;
import ru.yandex.praktikum.model.*;

public class TestLogin {
    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String name;
    private String email;
    private String password;

    public TestLogin() {
    }

    @Before
    public void setUp() throws Exception {
        //запуск драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //подготовка даннных
        userClient = new UserClient();
        user = UserGenerator.getRandomData();
        userClient.createNewUser(user);
        email = user.getEmail();
        password = user.getPassword();
    }

    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной странице")
    @Test
    public void TestLoginFromMainPage() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        objMainPage.transferToLoginFromMainPage();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.open();
        objLoginPage.fillLoginFormWithValidData(email, password);
        objLoginPage.transferToMainPageOnLogin();
        String orderButton = objMainPage.locateOrderButton().getText();
        Assert.assertEquals("Оформить заказ", orderButton);
    }

    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    @Test
    public void TestLoginFromAdminSection() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        objMainPage.selectAdminSection();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.fillLoginFormWithValidData(email, password);
        objLoginPage.transferToMainPageOnLogin();
        String orderButton = objMainPage.locateOrderButton().getText();
        Assert.assertEquals("Оформить заказ", orderButton);
    }

    @DisplayName("Проверка входа через кнопку в форме регистрации")
    @Test
    public void TestLoginFromRegistrationPage() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.open();
        objRegisterPage.openLoginPageFromRegistration();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.fillLoginFormWithValidData(email, password);
        objLoginPage.transferToMainPageOnLogin();
        MainPage objMainPage = new MainPage(driver);
        String orderButton = objMainPage.locateOrderButton().getText();
        Assert.assertEquals("Оформить заказ", orderButton);
    }

    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    @Test
    public void TestLoginFromForgotPasswordPage() {
        ForgotPasswordPage objForgotPasswordPage = new ForgotPasswordPage(driver);
        objForgotPasswordPage.open();
        objForgotPasswordPage.transferToLoginFromForgotPasswordPage();
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.fillLoginFormWithValidData(email, password);
        objLoginPage.transferToMainPageOnLogin();
        MainPage objMainPage = new MainPage(driver);
        String orderButton = objMainPage.locateOrderButton().getText();
        Assert.assertEquals("Оформить заказ", orderButton);
    }

    @After
    public void tearDown() throws Exception {
        UserLogin userLogin = new UserLogin(email, password);
        UserResponse response = userClient.login(userLogin).extract().as(UserResponse.class);
        userClient.deleteUser(response.getAccessToken());
        driver.quit();
    }
}
