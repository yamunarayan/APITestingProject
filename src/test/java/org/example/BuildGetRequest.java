package org.example;

import io.restassured.RestAssured;

import io.restassured.config.SSLConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BuildGetRequest {
    @Test
    public static void main (String[] args){
        goRest();
        RestAssured.baseURI="https://reqres.in/";
        RestAssured.basePath="api/users";

       SSLConfig sslConfig= RestAssured.config().getSSLConfig().relaxedHTTPSValidation();
        SSLConfig sslConfig1= RestAssured.config().getSSLConfig().allowAllHostnames();
         Response response=RestAssured.given().log().all().
                header("ContentType","application/json").
                when().
                get("2").
                then().log().all().
                 body("data.first_name", Matchers.is("Janet")).
                 time(Matchers.lessThan(1000l)).
                 statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();
        String email=jsonPath.get("data.email");
        System.out.println(email);
     //   System.out.println(response.prettyPrint());
        Assert.assertEquals(email,"janet.weaver@reqres.in");
    }

    public static void goRest(){
        RestAssured.baseURI="https://reqres.in/";
        RestAssured.basePath="api/users";
        Response response=RestAssured.given().log().all().
                header("ContentType","application/json").
                queryParam("page","2").
                when().
                get().
                then().log().all().
                statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        String email=jsonPath.get("data[0].email");
        System.out.println(email);
        //   System.out.println(response.prettyPrint());
        Assert.assertEquals(email,"michael.lawson@reqres.in");
    }
}
