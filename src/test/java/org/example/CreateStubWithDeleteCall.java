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

public class CreateStubWithDeleteCall {
    @Test
    public void deleteCall() throws JsonProcessingException {
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
        StubMapping deleteWithStaticBody = stubFor(delete(urlPathEqualTo("/api/v6/rest")).withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(202))
                .atPriority(3).persistent(true));

        StubMapping deleteWithBodyFile = stubFor(delete(urlPathEqualTo("/api/v6/rest")).withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(202).withBodyFile("model.json"))
                .atPriority(3).persistent(true));

        StubMapping deleteWithJsonBody = stubFor(delete(urlPathEqualTo("/api/v6/rest")).withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(202).withJsonBody(jsonNode))
                .atPriority(3).persistent(true));


        System.out.println (deleteWithJsonBody.getId());

        RestAssured.baseURI="http://localhost:8080";
        Response deleteResponse = RestAssured.given().log().all().headers("Content-Type","application/json").
                delete("/api/v6//deleteWithJsonBody.getId()").then().log().all().extract().response();


        deleteResponse.prettyPrint();

    }
}
