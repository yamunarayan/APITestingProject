package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class BuildPostCallUsingLomBok {
    public static void main(String[] args) throws IOException {
        Path path=Paths.get("./src/test/java/resources/request.json");
        byte[] bytes= Files.readAllBytes(path);
        ObjectMapper mapper = new ObjectMapper();
        ServiceNowRequestL serviceNowRequestL = mapper.readValue(bytes, ServiceNowRequestL.class);
        serviceNowRequestL.setShortDescription("fgd");
        serviceNowRequestL.setPriority("dgd");
        serviceNowRequestL.setPriority("1");

        HashMap<String, String> headerMap=new HashMap<String, String>();
        headerMap.put("Content-Type","application/json");
        headerMap.put("Accept","application/json");
        RestAssured.baseURI="https://dev106543.service-now.com/";
        RestAssured.basePath="/api/now/table/";
        RestAssured.authentication=RestAssured.basic("admin","^qqUL74RdtX/");

        CreateIncidentResponse incident = RestAssured.given().log().all().
                headers(headerMap).body(serviceNowRequestL).
                when().
                post("incident").
                then().log().all().
                statusCode(201).
                extract().response().as(CreateIncidentResponse.class);
        String sysId = incident.getResult().getSysId();
        String link = incident.getResult().getOpenedBy().getLink();
        String value = incident.getResult().getSysDomain().getValue();
        System.out.println(sysId);
        System.out.println(link);
        System.out.println(value);


    }
}
