package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.HashMap;

public class PostReq {
    public static void main(String[] args) {
        HashMap<String, String> headerobjMap = new HashMap<String, String>();
        headerobjMap.put("Content-Type", "application/json");
        headerobjMap.put("Accept", "application/json");

        String body = "{\n" +
                "    \"fields\": {\n" +
                "        \"project\": {\n" +
                "            \"key\": \"TES\"\n" +
                "        },\n" +
                "        \"summary\": \"Sample issue summary\",\n" +
                "        \"issuetype\": {\n" +
                "            \"name\": \"Bug\"\n" +
                "        },\n" +
                "        \"description\": {\n" +
                "            \"type\": \"doc\",\n" +
                "            \"version\": 1,\n" +
                "            \"content\": [\n" +
                "                {\n" +
                "                    \"type\": \"paragraph\",\n" +
                "                    \"content\": [\n" +
                "                        {\n" +
                "                            \"text\": \"description\",\n" +
                "                            \"type\": \"text\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";

        RestAssured.baseURI = "https://pavi143.atlassian.net";
        RestAssured.basePath = "/rest/api/3/";
        RestAssured.authentication=RestAssured.basic("yamuna.jayaraman@gmail.com","X4By27BOMZJw9vtofoJ27FEC");

        Response response = RestAssured.given().
                headers(headerobjMap).
                body(body).log().all().
                when().post("issue").
                then().log().all().
                statusCode(201).extract().response();
        System.out.println(response.prettyPrint());
        //response.then().body("job",Matchers.is("leader"));
       // response.then().body("name",Matchers.containsString("morpheus"));
    }
}