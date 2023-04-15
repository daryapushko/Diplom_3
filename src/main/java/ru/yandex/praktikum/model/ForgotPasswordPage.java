package ru.yandex.praktikum.model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final String URI_FORGOT_PASSWORD_PAGE = "https://stellarburgers.nomoreparties.site/forgot-password";
    private final By LOGIN_BUTTON_FROM_FPP = By.xpath("//a[@href='/login']");
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открыть страницу восстановления пароля")
    public void open() {
        driver.get(URI_FORGOT_PASSWORD_PAGE);
    }

    @Step("Переход на страницу логина со страницы восстановления пароля")
    public void transferToLoginFromForgotPasswordPage() {
        driver.findElement(LOGIN_BUTTON_FROM_FPP).click();
        driver.getCurrentUrl();
    }
}
