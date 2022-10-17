package org.example;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Rule;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class CreateMockProxying {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

@Test
    public void proxyingMockTest(){

        StubMapping stubMapping = stubFor(get(urlPathEqualTo("/api/users")).
                willReturn(aResponse().withStatus(200).proxiedFrom("https://reqres.in")));

        Response response = RestAssured.given().log().all().
                get("http://localhost:8080/api/users");
        response.prettyPrint();

    }
}
