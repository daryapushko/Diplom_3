package ru.yandex.praktikum.model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final String URI_PROFILE_PAGE = "https://stellarburgers.nomoreparties.site/account/profile";
    private final By LOGOUT_BUTTON_PP = By.xpath("//button[text() ='Выход']");
    private final By PROFILE_DATA = By.xpath("//div[@class = 'Profile_profile__3dzvr']");
    private final WebDriver driver;
    private String email;
    private String name;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void open() {
        driver.get(URI_PROFILE_PAGE);
    }

    @Step("Выйти из аккаунта по кнопке Выход")
    public void logoutFromProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON_PP));
        driver.findElement(LOGOUT_BUTTON_PP).click();
    }
    @Step("Получить адрес страницы профиля")
    public String getProfilePageUrl() {
        return URI_PROFILE_PAGE;
    }
    @Step("Найти раздел с даннными пользователя со страницы профиля")
    public WebElement locatePersonalData() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_DATA));
        return driver.findElement(PROFILE_DATA);
    }
}
