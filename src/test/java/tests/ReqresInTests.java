package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class ReqresInTests {

        /*
        1. Make request (POST) to https://reqres.in/api/login with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
        2. Get response { "token": "QpwL5tke4Pnpja7X4" }
        3. Check token is QpwL5tke4Pnpja7X4
     */

    @Test
    void successfulLoginTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unSuccessfulLoginWithMissingEmailTest() {
        String body = "{ \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void unSuccessfulLoginWithMissingPasswordTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    void unSuccessfulLoginWithEmptyDataTest() {
        String body = "{ \"email\": \"sydney@fife\" }";
        given()
                .log().uri()
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);
    }

    @Test
    void SuccessfulCreateUserTest() {
        String body = "{ \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\" }";
        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    void ListUsersTest() {
        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    void Test() {
        given()
                .log().uri()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }


}
