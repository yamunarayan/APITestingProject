package org.example;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateAMock {


    @Test
    public static void createStubAutomatically(){
       // WireMockServer wireMockServer = new WireMockServer(options().port(8089));
        WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(8080));
        wireMockServer.start();

        StubMapping stubMapping = stubFor(get(urlPathEqualTo("/api/v1/book")).
                willReturn(aResponse().withStatus(200).withBodyFile("model.json")).
                withHeader("Content-Type",containing("application/json")));

        System.out.println(stubMapping.getId());
        Response response = RestAssured.given().log().all().
                headers("Content-Type","application/json").
                get("http://localhost:8080/api/v1/book");

        response.prettyPrint();
        wireMockServer.stop();

    }
}
