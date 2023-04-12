package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage{
    private final WebDriver driver;
    public String getLoginPageUrl;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final String URI_LOGIN_PAGE = "https://stellarburgers.nomoreparties.site/login";
    private static final By INPUT_FIELDS = By.xpath("//div[@class = 'input__container']/div/input");
    private static final By LOGIN_BUTTON = By.xpath("//button[text() = 'Войти']");

    public void open() {
        driver.get(URI_LOGIN_PAGE);
    }

    public String getLoginPageUrl() {
        return driver.getCurrentUrl();
    }
    public void fillLoginFormWithValidData(String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(INPUT_FIELDS));
        driver.findElements(INPUT_FIELDS).get(0).clear();
        driver.findElements(INPUT_FIELDS).get(0).sendKeys(email);
        driver.findElements(INPUT_FIELDS).get(1).clear();
        driver.findElements(INPUT_FIELDS).get(1).sendKeys(password);
    }

    public String transferToMainPageOnLogin(){
        driver.findElement(LOGIN_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.invisibilityOfElementWithText(LOGIN_BUTTON, "Войти"));
        return driver.getCurrentUrl();
    }

}
