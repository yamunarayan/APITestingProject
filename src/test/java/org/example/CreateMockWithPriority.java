package org.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


public class CreateMockWithPriority {
@Test
    public static void createMock(){

        WireMockServer wireMockServer= new WireMockServer(wireMockConfig().port(8080));
        wireMockServer.start();

    StubMapping stubMapping = stubFor(post(urlPathEqualTo("/api/v1/players")).
            withHeader("Content-Type", matching("application/json")).
            withBasicAuth("admin", "admin").
            willReturn(aResponse().withStatus(200).withStatusMessage("Created").withBody("hello")).atPriority(2).persistent(true));

    RestAssured.baseURI="http://localhost:8080";

    Response post = RestAssured.given().log().all().headers("Content-Type", "application/json").
            post("/api/v1/players");
    post.prettyPrint();


}

}
