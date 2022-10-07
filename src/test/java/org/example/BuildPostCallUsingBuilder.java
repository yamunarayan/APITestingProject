package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class BuildPostCallUsingBuilder {
    public static void main(String[] args) throws IOException {

        HashMap<String, String> headerMap=new HashMap<String, String>();
        headerMap.put("Content-Type","application/json");
        headerMap.put("Accept","application/json");
        RestAssured.baseURI="https://dev106543.service-now.com/";
        RestAssured.basePath="/api/now/table/";
        RestAssured.authentication=RestAssured.basic("admin","^qqUL74RdtX/");
        ServiceNowRequestUsingBuilder serviceNowRequestUsingBuilderObj =  ServiceNowRequestUsingBuilder.
                builder()
                .shortDescription("SoftwareCate")
                .category("Software")
                .build();


        CreateIncidentResponse incident = RestAssured.given().log().all().
                headers(headerMap).body(serviceNowRequestUsingBuilderObj).
                when().
                post("incident").
                then().log().all().
                statusCode(201).
                extract().response().as(CreateIncidentResponse.class);
       System.out.println(incident.getResult().getSysId());


    }
}
