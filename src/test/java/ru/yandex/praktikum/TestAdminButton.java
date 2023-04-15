package ru.yandex.praktikum;

import io.qameta.allure.Step;
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
import ru.yandex.praktikum.model.User;
import ru.yandex.praktikum.model.UserLogin;

public class TestAdminButton {
    private WebDriver driver;
    private UserLogin userLogin;
    private User user;
    private UserClient userClient;
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
        user = UserGenerator.getRandomData();
        userClient.createNewUser(user);
        //логин с новым пользователем
        email = user.getEmail();
        password = user.getPassword();}


    @DisplayName("Проверка перехода по клику на «Личный кабинет».")
    @Test
    public void testTransferToAdminSection() {
        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.open();
        objLoginPage.fillLoginFormWithValidData(email, password);
        objLoginPage.transferToMainPageOnLogin();
        //переход в личный кабинет с главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.selectAdminSection();
        //отображение страницы профиля
        ProfilePage objProfilePage = new ProfilePage(driver);
        System.out.println(objProfilePage.getProfilePageUrl());
        boolean isProfileDataAvailable = objProfilePage.locatePersonalData().isDisplayed();
        Assert.assertTrue("Страница профиля не показана", isProfileDataAvailable);
    }

    @After
    public void tearDown() throws Exception {
        UserLogin userLogin = new UserLogin(email, password);
        UserResponse response = userClient.login(userLogin).extract().as(UserResponse.class);
        userClient.deleteUser(response.getAccessToken());
        driver.quit();
    }
}
