package org.example;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Rule;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class DiffTypesOfResponse {
    @Rule
    public WireMockRule wireMockRule= new WireMockRule(8080);
    @Test
    public static void responseTest(){

        StubMapping stubMapping = stubFor(post(urlPathEqualTo("/api/users")).
                withHeader("Content-Type", containing("application/json")).
                willReturn(badRequest()));

        StubMapping stubMapping1 = stubFor(post(urlPathEqualTo("/api/users")).
                withHeader("Content-Type", containing("application/json"))
                .willReturn(serverError()));

        stubFor(post(urlPathEqualTo("/api/users")).
                withHeader("Content-Type", containing("appilication/json"))
                .willReturn(serviceUnavailable()));

        Response response = RestAssured.given().log().all().headers("Content-Type", "application/json").
                post("http://localhost:8080/api/users").then().log().all().extract().response();

        response.prettyPrint();

    }
}
