package org.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.bytebuddy.asm.MemberSubstitution;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class createMockWithJson {
    @Test
    public static void creteMockWithJsonTest() throws JsonProcessingException {

        String body = "{\n" +
                "  \"id\": 2,\n" +
                "  \"email\": \"janet.weaver@reqres.in\",\n" +
                "  \"first_name\": \"Janet\",\n" +
                "  \"last_name\": \"Weaver\"\n" +
                "\n" +
                "}";
        ObjectMapper mapper= new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(body);


        WireMockServer wireMockServer= new WireMockServer(wireMockConfig().port(8080)) ;
        wireMockServer.start();

        StubMapping stubMapping = stubFor(post(urlPathEqualTo("/api/vi/users")).withHeader("Content-Type", matching("application/json")).
                withBasicAuth("admin", "admin").willReturn(aResponse().withStatus(200).
                        withJsonBody(jsonNode)).atPriority(2).persistent(true));

       System.out.println( stubMapping.getPriority());

        RestAssured.baseURI="http://localhost:8080";

        Response post = RestAssured.given().log().all().headers("Content-Type", "application/json").
                headers("Authorization","Basic YWRtaW46YWRtaW4=").
                post("/api/vi/users");
        post.prettyPrint();
    }


}
