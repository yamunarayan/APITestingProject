package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.io.File;
import java.util.HashMap;

public class BuildPostCall {
    public static void main(String[] args) throws JsonProcessingException {
        String body= "{\"short_description\":\"test\"}";

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> type= new TypeReference<>(){};

HashMap<String, String> map = mapper.readValue(body,type);
map.put("short_description","Edited");



        HashMap<String, String>headerMap=new HashMap<String, String>();
        headerMap.put("Content-Type","application/json");
        headerMap.put("Accept","application/json");


        RestAssured.baseURI="https://dev106543.service-now.com/";
        RestAssured.basePath="/api/now/table/";
        RestAssured.authentication=RestAssured.basic("admin","^qqUL74RdtX/");

        Response responseIncident = RestAssured.given().log().all().
                headers(headerMap).body(map).
                when().
                post("incident").
                then().log().all().
                statusCode(201).extract().response();
    System.out.println(responseIncident.prettyPrint());
        responseIncident.then().body("result.short_description", Matchers.is("Edited"));
        File file = new File("./src/test/java/resources/Schema.json");
            responseIncident.then().body(JsonSchemaValidator.matchesJsonSchema(file));
        //responseIncident.then().body("name",Matchers.containsString("morpheus"));
    }
}
