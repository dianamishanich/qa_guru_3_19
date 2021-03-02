package tests;

import io.restassured.RestAssured;
import model.User;
import model.UserRequest;
import model.UserResponse;
import model.UsersResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReqresInTests {

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    @DisplayName("SINGLE USER")
    void singleUserTest() {
        UserResponse userResponse = given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(UserResponse.class);

        assertTrue(userResponse.getData().getId() == 2);

    }

    @Test
    @DisplayName("LIST USERS")
    void listUsersTest() {
        UsersResponse usersResponse = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(UsersResponse.class);

        assertTrue(usersResponse.getPage() == 5);

    }

    @Test
    @DisplayName("CREATE USER")
    void createUserTest() {

        UserRequest newUser = new UserRequest();
        newUser.setEmail("test@test.test");
        newUser.setPassword("password");

        User user = given()
                .when()
                .body(newUser)
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(User.class);

        assertNotNull(user.getId());

    }

}
