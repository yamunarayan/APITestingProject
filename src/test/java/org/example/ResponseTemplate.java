package org.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ResponseTemplate {
    @Rule
    public WireMockRule wm = new WireMockRule(new WireMockConfiguration().port(8080).
            extensions(new ResponseTemplateTransformer(false)));
    @Test
    public void createStub(){
        String bodyJson= "{\n" +
                "\"name\":\"Testing\",\n" +
                "\"Occupation\": \"Software Testing\",\n" +
                "\"Country\": \"India\",\n" +
                "\"Rank\": \"23\"\n" +
                "}";

        stubFor(post(urlPathEqualTo("/api/usersid")).willReturn(aResponse().
                withStatus(201).withStatusMessage("Created").
                withHeader("Content-Type","application/json").
               // withBody("{{jsonPath request.body '$.name'}} ").
                       withBody("{{request.path}}").
                withTransformers("response-template")));

        RestAssured.baseURI="http://localhost:8080";
        Response postResponse = RestAssured.given().log().all().when().body(bodyJson).post("/api/usersid");
        postResponse.prettyPrint();
        System.out.println(postResponse.statusCode());

    }
}
