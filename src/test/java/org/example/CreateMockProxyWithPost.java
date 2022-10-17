package org.example;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Rule;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CreateMockProxyWithPost {
    @Rule
    public WireMockRule wireMockRule= new WireMockRule(8080);

    @Test
    public static void proxyWithPost(){

        StubMapping stubMapping = stubFor(post(urlPathEqualTo("/api/users")).
                withHeader("Content-Type", matching("application/json")).
                willReturn(aResponse().withStatus(201).proxiedFrom("https://reqres.in")));

        Response post = RestAssured.given().log().all().headers("Content-Type", "application/json").
                post("http://localhost:8080/api/users");

        post.prettyPrint();


    }
}
