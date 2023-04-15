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

public class TestRegistrationValidationMessage {
    private WebDriver driver;
    private String invalidPassword;
    private String blankPassword;

    @Before
    public void setUp() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--ignore-certificate-errors", "--disable-extensions", "--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        this.invalidPassword = UserGenerator.getRandomInvalidPassword().getPassword();
        this.blankPassword = UserGenerator.getRandomBlankPassword().getPassword();
    }

    @DisplayName("Проверка ошибки для некорректного пароля. Минимальный пароль — шесть символов.")
    @Test
    public void TestRegisterPasswordValidationMessage() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.open();
        String messageDisplayed = objRegisterPage.receivePasswordValidationMessage(invalidPassword).getText();
        Assert.assertEquals("Не работает валидация", "Некорректный пароль", messageDisplayed);
    }

    @DisplayName("Проверка ошибки для пустого пароля. Минимальный пароль — шесть символов.")
    @Test
    public void TestBlankPasswordValidationMessage() {
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objRegisterPage.open();
        String messageDisplayed = objRegisterPage.receivePasswordValidationMessage(blankPassword).getText();
        Assert.assertEquals("Не работает валидация", "Некорректный пароль", messageDisplayed);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
