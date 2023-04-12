package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String URI_REGISTER_PAGE = "https://stellarburgers.nomoreparties.site/register";
    private static final By LOGIN_BUTTON_FROM_RP = By.xpath("//a[@href='/login']");
    private static final By REGISTER_INPUT_FIELDS = By.xpath("//div[@class = 'input__container']/div/input");
    private static final By REGISTER_BUTTON = By.xpath("//button[text() = 'Зарегистрироваться']");
    private static final By PASSWORD_VALIDATION_MESSAGE = //By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p");
            By.xpath("//p[text() = 'Некорректный пароль']");

    public void open() {
        driver.get(URI_REGISTER_PAGE);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(REGISTER_BUTTON));
    }

    public void fillRegisterFormWIthValidData(String name, String email, String password) {
        driver.findElements(REGISTER_INPUT_FIELDS).get(0).clear();
        driver.findElements(REGISTER_INPUT_FIELDS).get(0).sendKeys(name);
        driver.findElements(REGISTER_INPUT_FIELDS).get(1).clear();
        driver.findElements(REGISTER_INPUT_FIELDS).get(1).sendKeys(email);
        driver.findElements(REGISTER_INPUT_FIELDS).get(2).clear();
        driver.findElements(REGISTER_INPUT_FIELDS).get(2).sendKeys(password);
    }
    public String transferToLoginOnRegistration(){
        driver.findElement(REGISTER_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlMatches("https://stellarburgers.nomoreparties.site/login"));
        return driver.getCurrentUrl();
    }
    public WebElement receivePasswordValidationMessage (String password){
        driver.findElements(REGISTER_INPUT_FIELDS).get(2).clear();
        driver.findElements(REGISTER_INPUT_FIELDS).get(2).sendKeys(password);
        driver.findElement(REGISTER_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_VALIDATION_MESSAGE));
        return driver.findElement(PASSWORD_VALIDATION_MESSAGE);
    }

    public void openLoginPageFromRegistration(){
        driver.findElement(LOGIN_BUTTON_FROM_RP).click();
        driver.getCurrentUrl();
    }
}