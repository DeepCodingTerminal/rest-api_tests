package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

public class SelenoidTests {
    @Test
    void checkTotal() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }
    @Test
    void checkWithSomeLogsTotal() {
        given()
                .log().uri()
//                .log().body()
        .when()
                .get("https://selenoid.autotests.cloud/status")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20));
    }
    @Test
    void checkChromeVersion() {
        given()
                .log().uri()
//                .log().body()
        .when()
                .get("https://selenoid.autotests.cloud/status")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(20))
                .body("browsers.chrome", hasKey("100.0"));
    }
    @Test
    void checkResponseBadPractice() {
        String response = given()
                        .log().uri()
        //                .log().body()
                        .when()
                        .get("https://selenoid.autotests.cloud/status")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
    }
}