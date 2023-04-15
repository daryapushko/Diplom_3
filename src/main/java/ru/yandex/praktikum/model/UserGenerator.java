package ru.yandex.praktikum.model;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

public class UserGenerator {
    @Step("Генерация случайного пользователя")
    public static User getRandomData() {
        String email = RandomStringUtils.randomAlphanumeric(10).toLowerCase(Locale.ROOT) + "@mail.ru";
        String password = RandomStringUtils.randomAlphanumeric(10);
        String name = RandomStringUtils.randomAlphanumeric(10);
        return new User(email, password, name);
    }
    @Step("Генерация случайного валидного email пользователя")
    public static User getRandomEmail() {
        String email = RandomStringUtils.randomAlphanumeric(5).toLowerCase(Locale.ROOT) + "@mail.ru";
        User user = new User();
        user.setEmail(email);
        return user;
    }
    @Step("Генерация случайного валидного пароля пользователя")
    public static User getRandomValidPassword() {
        String password = RandomStringUtils.randomAlphanumeric(6);
        User user = new User();
        user.setPassword(password);
        return user;
    }
    @Step("Генерация случайного невалидного пароля пользователя")
    public static User getRandomInvalidPassword() {
        String password = RandomStringUtils.randomAlphanumeric(5);
        User user = new User();
        user.setPassword(password);
        return user;
    }
    @Step("Генерация случайного невалидного пароля пользователя, содержащегго только пробел")
    public static User getRandomBlankPassword() {
        String password = " ";
        User user = new User();
        user.setPassword(password);
        return user;
    }
    @Step("Генерация случайного валидного имени пользователя")
    public static User getRandomName() {
        String name = RandomStringUtils.randomAlphanumeric(5);
        User user = new User();
        user.setName(name);
        return user;
    }
}
