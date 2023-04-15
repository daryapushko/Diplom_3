package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.model.*;
import ru.yandex.praktikum.client.*;

public class TestRegistrationPage {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private String name;
    private String email;
    private String password;

    @Before
    public void setUp() throws Exception {
        //запуск драйвера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //подготовка данных
        userClient = new UserClient();
        this.name = UserGenerator.getRandomName().getName();
        this.email = UserGenerator.getRandomEmail().getEmail();
        this.password = UserGenerator.getRandomValidPassword().getPassword();
    }

    @DisplayName("Проверка успешной регистрации")
    @Test
    public void TestRegisterWithValidData() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.open();
        objRegisterPage.fillRegisterFormWIthValidData(name, email, password);
        String loginUri = objRegisterPage.transferToLoginOnRegistration();
        LoginPage objLoginPage = new LoginPage(driver);
        String expectedLoginUri = objLoginPage.getLoginPageUrl();
        Assert.assertEquals("Страница логина не показана", expectedLoginUri, loginUri);
    }

    @After
    public void tearDown() throws Exception {
        UserLogin userLogin = new UserLogin(email, password);
        UserResponse response = userClient.login(userLogin).extract().as(UserResponse.class);
        userClient.deleteUser(response.getAccessToken());
        driver.quit();
    }
}
