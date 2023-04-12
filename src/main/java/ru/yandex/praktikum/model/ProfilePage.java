package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
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

    private static final String URI_PROFILE_PAGE = "https://stellarburgers.nomoreparties.site/account/profile";
    private static final By LOGOUT_BUTTON_PP = By.xpath("//button[text() ='Выход']");
    private static final By PROFILE_DATA = By.xpath("//div[@class = 'Profile_profile__3dzvr']");

    public void open() {
        driver.get(URI_PROFILE_PAGE);
    }
    public void logoutFromProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON_PP));
        driver.findElement(LOGOUT_BUTTON_PP).click();
    }
    public String getUriProfilePage(){
        return URI_PROFILE_PAGE;
    }

    public WebElement locatePersonalData(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(PROFILE_DATA));
        return driver.findElement(PROFILE_DATA);
    }
}
