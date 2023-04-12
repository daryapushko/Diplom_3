package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;

    private static final String URI_FORGOT_PASSWORD_PAGE = "https://stellarburgers.nomoreparties.site/forgot-password";
    private static final By LOGIN_BUTTON_FROM_FPP = By.xpath("//a[@href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URI_FORGOT_PASSWORD_PAGE);
    }
    public void transferToLoginFromForgotPasswordPage() {
        driver.findElement(LOGIN_BUTTON_FROM_FPP).click();
        driver.getCurrentUrl();
    }
}
