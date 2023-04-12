# Diplom_3
## Используемые библиотеки:
- Java 11
- Maven
- JUnit 4
- Selenium 4
- Allure 
- RestAssured
- Google Chrome 109.0.5414.119
- Яндекс.Браузер 23.3.0.2318 (64-bit) (требует ChromiumDriver 110.0.0.0)

[Тестируемое приложение](https://stellarburgers.nomoreparties.site/)


### Дополнительные замечания
По умолчанию для тестов используется Google Chrome.
Для запуска тестов в Яндекс.Браузере, используйте секцию ниже (закоментируйте секцию в тесте для Google Chrome c аннотацией @Before).
В ресурсных файлах директории тестов доступен необходимый драйвер.
```
System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
ChromeOptions options = new ChromeOptions();
options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
driver = new ChromeDriver(options);
//driver.navigate().to("https://stellarburgers.nomoreparties.site/login"); - запуск для примера
````