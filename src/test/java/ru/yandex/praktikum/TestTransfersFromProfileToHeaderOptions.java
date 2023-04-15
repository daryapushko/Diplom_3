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

public class TestTransfersFromProfileToHeaderOptions {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
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
        this.user = new User();
        user.setName(UserGenerator.getRandomName().getName());
        user.setEmail(UserGenerator.getRandomEmail().getEmail());
        user.setPassword(UserGenerator.getRandomValidPassword().getPassword());
        userClient.createNewUser(user);
        email = user.getEmail();
        password = user.getPassword();
        //логин с новым пользователем
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.open();
        objLoginPage.fillLoginFormWithValidData(email, password);
        objLoginPage.transferToMainPageOnLogin();

    }

    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на логотип")
    @Test
    public void TestTransferFromAdminToMainPageWithLogo() {
        //открыть главную страницу и перейти в профиль
        MainPage objMainPage = new MainPage(driver);
        objMainPage.selectAdminSection();
        //переход из профиля на главную страницу по клику на логотип
        Header objHeader = new Header(driver);
        objHeader.selectLogo();
        objHeader.getCurrentUrl();
        Assert.assertEquals(objMainPage.getCurrentUrl(), objHeader.getCurrentUrl());
    }

    @DisplayName("Проверка перехода из личного кабинета в конструктор по клику на «Конструктор»")
    @Test
    public void TestTransferFromAdminToMainPageWithConstructorLink() {
        //открыть главную страницу и перейти в профиль
        MainPage objMainPage = new MainPage(driver);
        objMainPage.selectAdminSection();
        //переход из профиля на главную страницу по клику на «Конструктор»
        Header objHeader = new Header(driver);
        objHeader.selectConstructor();
        objHeader.getCurrentUrl();
        Assert.assertEquals(objMainPage.getCurrentUrl(), objHeader.getCurrentUrl());
    }

    @After
    public void tearDown() throws Exception {
        UserLogin userLogin = new UserLogin(email, password);
        UserResponse response = userClient.login(userLogin).extract().as(UserResponse.class);
        userClient.deleteUser(response.getAccessToken());
        driver.quit();
    }
}
