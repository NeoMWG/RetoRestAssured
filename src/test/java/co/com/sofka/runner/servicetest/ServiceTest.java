package co.com.sofka.runner.servicetest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

class ServiceTest {

    @Test
    void serviceVersion1() {

        String response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("https://reqres.in/api/users")
                .then()
                .extract()
                .asString();

        System.out.println(response);
    }

    @Test
    void serviceVersion2() {

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(200)
                .body("name", notNullValue());
    }

    @Test
    void serviceVersion3() {

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", notNullValue());
    }

    @Test
    void serviceVersion4() {

        RestAssured
                .given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo("morpheus"));
    }

    @Test
    void serviceVersion5() {
        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}")
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"));
    }

    @Test
    void serviceVersion6() {
        RestAssured
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}")
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(containsString("Missing password"));
    }
}
