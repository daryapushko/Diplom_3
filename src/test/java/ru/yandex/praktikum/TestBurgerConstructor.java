package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.model.MainPage;
import java.util.concurrent.TimeUnit;

public class TestBurgerConstructor {
    private WebDriver driver;
    String expectedAttribute = "tab_type_current";

    @Before
    public void setUp() throws Exception {
        //запуск браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage", "--remote-allow-origins=*");
        driver = new ChromeDriver(options);


}
    @DisplayName("Проверка того, что работают переходы к разделам: Соусы")
    @Test
    public void shouldLocateSauceInBurgerConstructor() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        String attributeForCurrentElem = objMainPage.touchIngredientSauce().getAttribute("class");
        Assert.assertTrue("Элемент не является текущим", attributeForCurrentElem.contains(expectedAttribute));
    }
    @DisplayName("Проверка того, что работают переходы к разделам: Булки")
    @Test
    public void shouldLocateBunInBurgerConstructor() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        String attributeForCurrentElem = objMainPage.touchIngredientBun().getAttribute("class");
        Assert.assertTrue("Элемент не является текущим", attributeForCurrentElem.contains(expectedAttribute));
    }
    @DisplayName("Проверка того, что работают переходы к разделам: Начинки")
    @Test
    public void shouldLocateFillingInBurgerConstructor() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        String attributeForCurrentElem = objMainPage.touchIngredientFilling().getAttribute("class");
        Assert.assertTrue("Элемент не является текущим", attributeForCurrentElem.contains(expectedAttribute));
    }

    @After
    public void tearDown() throws Exception {
            driver.quit();
    }
}
