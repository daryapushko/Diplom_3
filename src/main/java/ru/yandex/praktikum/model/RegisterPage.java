package ru.yandex.praktikum.model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class RegisterPage {
    public final String URI_REGISTER_PAGE = "https://stellarburgers.nomoreparties.site/register";
    private final By LOGIN_BUTTON_FROM_RP = By.xpath("//a[@href='/login']");
    private final By REGISTER_INPUT_FIELDS = By.xpath("//div[@class = 'input__container']/div/input");
    private final By REGISTER_BUTTON = By.xpath("//button[text() = 'Зарегистрироваться']");
    private final By PASSWORD_VALIDATION_MESSAGE = By.xpath("//p[text() = 'Некорректный пароль']");
    private final WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get(URI_REGISTER_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
    }
    @Step("Заполннить поля регистрации данными")
    public void fillRegisterFormWIthValidData(String name, String email, String password) {
        driver.findElements(REGISTER_INPUT_FIELDS).get(0).clear();
        driver.findElements(REGISTER_INPUT_FIELDS).get(0).sendKeys(name);
        driver.findElements(REGISTER_INPUT_FIELDS).get(1).clear();
        driver.findElements(REGISTER_INPUT_FIELDS).get(1).sendKeys(email);
        driver.findElements(REGISTER_INPUT_FIELDS).get(2).clear();
        driver.findElements(REGISTER_INPUT_FIELDS).get(2).sendKeys(password);
    }
    @Step("Перейти на страницу логина по клику на кнопку регистрации")
    public String transferToLoginOnRegistration() {
        driver.findElement(REGISTER_BUTTON).isDisplayed();
        driver.findElement(REGISTER_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlMatches("https://stellarburgers.nomoreparties.site/login"));
        return driver.getCurrentUrl();
    }
    @Step("Получить валидационное сообщение при вводе некорректного пароля")
    public WebElement receivePasswordValidationMessage(String password) {
        driver.findElements(REGISTER_INPUT_FIELDS).get(2).clear();
        driver.findElements(REGISTER_INPUT_FIELDS).get(2).sendKeys(password);
        driver.findElement(REGISTER_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_VALIDATION_MESSAGE));
        return driver.findElement(PASSWORD_VALIDATION_MESSAGE);
    }
    @Step("Получить адрес страницы после нажатия на кнопку логина на странице регистрации")
    public void openLoginPageFromRegistration() {
        driver.findElement(LOGIN_BUTTON_FROM_RP).click();
        driver.getCurrentUrl();
    }
}