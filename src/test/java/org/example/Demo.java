package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class Demo {

    @Test
    public void getUserList() {
        RestAssured.baseURI = "https://reqres.in/api";

        // GET request to the "/users" endpoint
        Response response = given()
            .when()
                .get("/users")
            .then()
                .statusCode(200) // Verify Status Code
                .body("total_pages",greaterThanOrEqualTo(1))
                .body("data.size()", greaterThan(0))
                .body("data.id", hasSize(greaterThan(0)))
                .body("data.first_name", everyItem(not(emptyOrNullString()))) // Verify User Names
                .body("data.last_name", everyItem(not(emptyOrNullString())))

//                .body("data.email", everyItem(matchesRegex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"))) // Verify Email Format
                .extract()
                .response();
    }
    @Test
    public void getUser(){
        RestAssured.baseURI = "https://reqres.in/api";

        // GET request to the "/users/1" endpoint
        Response response =
                given()
                .when()
                        .get("/users/1")
                .then()
                    .statusCode(200)
                    .body("id", equalTo(1))
                    .body("first_name", equalTo("George"))
                    .body("last_name", equalTo("Bluth"))
                    .body("avatar", not(emptyOrNullString()))
                    .body("email", not(emptyOrNullString()))

                    .extract()
                    .response();
    }

}