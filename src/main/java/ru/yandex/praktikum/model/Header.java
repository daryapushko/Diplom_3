package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private final WebDriver driver;
    public Header(WebDriver driver) {
        this.driver = driver;
    }
    private static final By LOGO = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private static final By CONSTRUCTOR = By.xpath("//ul[@class='AppHeader_header__list__3oKJj']/li[1]");
    public void selectLogo() {
        driver.findElement(LOGO).click();
    }
    public void selectConstructor() {
        driver.findElement(CONSTRUCTOR).click();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
