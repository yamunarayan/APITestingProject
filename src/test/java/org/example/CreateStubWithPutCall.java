package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.impl.conn.Wire;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class CreateStubWithPutCall {
    @Test
    public void putCallwithStub() throws JsonProcessingException {

        WireMockServer wireMockServer= new WireMockServer();
        wireMockServer.start();

        String jsonString="{\n" +
                "  \"id\": 2,\n" +
                "  \"email\": \"janet.weaver@reqres.in\",\n" +
                "  \"first_name\": \"Janet\",\n" +
                "  \"last_name\": \"Weavering- Editing\"\n" +
                "\n" +
                "}" ;

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString);

        StubMapping withStaticText = stubFor(put(urlPathEqualTo("/api/v5/Test")).
                withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(204).withBody("Edited")).atPriority(3).persistent(true));


        StubMapping withBodyFile = stubFor(put(urlPathEqualTo("/api/v5/Test")).withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(204).
                        withBodyFile("model/json")).atPriority(2).persistent(true));

        stubFor(put(urlPathEqualTo("/api/v5/Test")).withHeader("Content-Type", matching("application/json"))
                .willReturn(aResponse().withStatus(204).withJsonBody(jsonNode)).atPriority(1).persistent(true));


        RestAssured.baseURI="http://localhost:8080";
        Response putResponse = RestAssured.given().log().all().
                header("Content-Type", "application/json").get("/api/v3/users").
                then().log().all().extract().response();

        System.out.println(putResponse.getStatusCode());
        putResponse.prettyPrint();

    }
}
