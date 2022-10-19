package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class CreateStubWithPostCall {
    @Test
    public void postCallStub() throws JsonProcessingException {
        WireMockServer wireMockServer= new WireMockServer(wireMockConfig().port(8080));
        wireMockServer.start();

           String jsonString="{\n" +
                   "  \"id\": 2,\n" +
                   "  \"email\": \"janet.weaver@reqres.in\",\n" +
                   "  \"first_name\": \"Janet\",\n" +
                   "  \"last_name\": \"Weaver\"\n" +
                   "\n" +
                   "}" ;

        ObjectMapper mapper= new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString);
        StubMapping withStaticString = stubFor(post(urlPathEqualTo("/api/v3/users")).withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(201).withBody("Hi")).atPriority(3).persistent(true));

        StubMapping withBodyFile = stubFor(post(urlPathEqualTo("/api/v3/users")).withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(201).withBodyFile("model.json")).atPriority(2).persistent(true));

        stubFor(post(urlPathEqualTo("/api/v3/users")).withHeader("Content-Type",matching("application/json"))
                .willReturn(aResponse().withStatus(201).withJsonBody(jsonNode)).atPriority(1).persistent(true));

        RestAssured.baseURI="http://localhost:8080";
        Response post = RestAssured.given().log().all().headers("Content-Type", "application/json").
                post("/api/v3/users");
   System.out.println( post.statusCode());
   post.prettyPrint();

    }


}
