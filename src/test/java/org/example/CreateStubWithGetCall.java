package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CreateStubWithGetCall {
    @Test
    public void getCallForStubs() throws JsonProcessingException {

        WireMockServer wireMockServer= new WireMockServer();
        wireMockServer.start();
        String jsonString="{\n" +
                "  \"id\": 2,\n" +
                "  \"email\": \"janet.weaver@reqres.in\",\n" +
                "  \"first_name\": \"Janet\",\n" +
                "  \"last_name\": \"Weavering\"\n" +
                "\n" +
                "}" ;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString);

        StubMapping withStaticString = stubFor(get(urlPathEqualTo("/api/v4/books")).
                withHeader("Content-Type", containing("application/json")).withBasicAuth("admin","admin")
                .willReturn(aResponse().withFixedDelay(3000).withStatus(200).withBody("he, hello")).atPriority(4).persistent(true));

        StubMapping withBodyFile = stubFor(get(urlPathEqualTo("/api/v4/books")).
                withHeader("Content-Type", matching("application/json")).
                        withBasicAuth("admin","admin").
                willReturn(aResponse().withStatus(200).withBodyFile("model.json")).
                atPriority(3).persistent(true));

        stubFor(get(urlPathEqualTo("/api/v4/books")).
                withHeader("Content-Type",matching("application/json")).
                        withBasicAuth("admin","admin").
                willReturn(aResponse().withStatus(200).withJsonBody(jsonNode)).atPriority(2).persistent(true));

        RestAssured.baseURI="http://localhost:8080";
        Response response = RestAssured.given().log().all().
                headers("Content-Type","application/json").
                headers("admin","admin").
                get("/api/v4/books");
       System.out.println( response.getStatusCode());
       response.prettyPrint();
    }
}
