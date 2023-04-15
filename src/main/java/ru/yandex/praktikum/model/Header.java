package ru.yandex.praktikum.model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    private final By LOGO = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");
    private final By CONSTRUCTOR = By.xpath("//ul[@class='AppHeader_header__list__3oKJj']/li[1]");
    private final WebDriver driver;
    public Header(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Нажать на логотип")
    public void selectLogo() {
        driver.findElement(LOGO).click();
    }
    @Step("Нажать на конструктор бургера")
    public void selectConstructor() {
        driver.findElement(CONSTRUCTOR).click();
    }
    @Step("Получить текущий адрес страницы")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
