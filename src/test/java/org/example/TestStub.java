package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class TestStub {
    @Test
    public static void getRecordedStub(){

        RestAssured.baseURI="http://localhost:8080";

        Response response = RestAssured.given().log().all().
                get("/api/users");

        response.prettyPrint();
    }
}
