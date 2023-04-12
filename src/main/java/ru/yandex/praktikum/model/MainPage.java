package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.FileStore;
import java.time.Duration;
public class MainPage {
    private final WebDriver driver;
    private static final String URI_MAIN_PAGE = "https://stellarburgers.nomoreparties.site/";
     private static final By ADMIN_BUTTON = By.xpath("//a[@href='/account']");
    private static final By ACCOUNT_LOGIN = By.xpath("//button[text() ='Войти в аккаунт']");
    private static final By BUN_PARENT = By.xpath("//div[span[text() = 'Булки']]");
    private static final By BUN = By.xpath("//span[text() = 'Булки']");
    private static final By SAUCE_PARENT = By.xpath("//div[span[text() = 'Соусы']]");
    private static final By SAUCE = By.xpath("//span[text() = 'Соусы']");
    private static final By FILLING = By.xpath("//span[text() = 'Начинки']");
    private static final By FILLING_PARENT = By.xpath("//div[span[text() = 'Начинки']]");
    private static final By ORDER_BUTTON = By.xpath("//button[text() = 'Оформить заказ']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get(URI_MAIN_PAGE);
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
    public String selectAdminSection() {
        driver.findElement(ADMIN_BUTTON).click();
        return driver.getCurrentUrl();
    }

    public WebElement touchIngredientSauce() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(SAUCE));
        driver.findElement(FILLING).click();
        driver.findElement(SAUCE).click();
        return driver.findElement(SAUCE_PARENT);
    }

    public WebElement touchIngredientBun() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(BUN));
        driver.findElement(SAUCE).click();
        driver.findElement(BUN).click();
        return driver.findElement(BUN_PARENT);
    }

    public WebElement touchIngredientFilling() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(FILLING));
        driver.findElement(SAUCE).click();
        driver.findElement(FILLING).click();
        return driver.findElement(FILLING_PARENT);
    }

    public String transferToLoginFromMainPage() {
        driver.findElement(ACCOUNT_LOGIN).isEnabled();
        driver.findElement(ACCOUNT_LOGIN).isSelected();
        return driver.findElement(ACCOUNT_LOGIN).getText();
    }
    public WebElement locateOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_BUTTON));
        return driver.findElement(ORDER_BUTTON);
    }

    public WebElement locateLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_LOGIN));
        return driver.findElement(ACCOUNT_LOGIN);
    }
}
