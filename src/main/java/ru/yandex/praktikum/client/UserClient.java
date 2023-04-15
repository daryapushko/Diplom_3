package ru.yandex.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.client.base.ClientConfiguration;
import ru.yandex.praktikum.model.User;
import ru.yandex.praktikum.model.UserResponse;
import ru.yandex.praktikum.model.UserLogin;

import static io.restassured.RestAssured.given;

public class UserClient extends ClientConfiguration {
    private static final String USER_URI = URI + "/api/auth/";

    @Step("Регистрация пользователя {user}")
    public ValidatableResponse createNewUser(User user) {
        return given()
                .spec(getHeader())
                .body(user)
                .when()
                .post(USER_URI + "register")
                .then();
    }

    @Step("Логин пользователем {userLogin}")
    public ValidatableResponse login(UserLogin userLogin) {
        return given()
                .spec(getHeader())
                .body(userLogin)
                .when()
                .post(USER_URI + "login")
                .then();
    }

    @Step("Удаление пользователя {userResponse}")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getHeaderwithAuthorisation(accessToken))
                .when()
                .delete(USER_URI + "user")
                .then();
    }
}
