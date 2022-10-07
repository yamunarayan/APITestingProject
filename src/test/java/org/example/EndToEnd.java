package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class EndToEnd {
    public String sysId;
    public String number;
    @Test
    public void createIncident(){
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
         sysId = incident.getResult().getSysId();
         number = incident.getResult().getNumber();

    }
@Test
    public void updateIncident(){
        HashMap<String, String> headerMap=new HashMap<String, String>();
        headerMap.put("Content-Type","application/json");
        headerMap.put("Accept","application/json");
        RestAssured.baseURI="https://dev106543.service-now.com/";
        RestAssured.basePath="/api/now/table/";
        RestAssured.authentication=RestAssured.basic("admin","^qqUL74RdtX/");
        ServiceNowRequestUsingBuilder serviceNowRequestUsingBuilderObj =  ServiceNowRequestUsingBuilder.
                builder()
                .shortDescription("SoftwareCate_Updated")
                .category("Software")
                .build();


        Response response = RestAssured.given().log().all().
                headers(headerMap).body(serviceNowRequestUsingBuilderObj).
                when().
                put("incident/"+sysId).
                then().log().all().
                statusCode(200).
                extract().response();

    }
@Test
    public void getAllIncident(){
        HashMap<String, String> headerMap=new HashMap<String, String>();
        headerMap.put("Content-Type","application/json");
        headerMap.put("Accept","application/json");
        RestAssured.baseURI="https://dev106543.service-now.com/";
        RestAssured.basePath="/api/now/table/";
        RestAssured.authentication=RestAssured.basic("admin","^qqUL74RdtX/");

        Response response = RestAssured.given().log().all().
                headers(headerMap).
                queryParams("sysparm_fields","number,sys_id,category,short_description").
                when().
                get("incident").
                then().log().all().
                statusCode(200).
                extract().response();
        response.then().body("result.number",Matchers.hasItem(number));



    }
    @Test(dependsOnMethods = "updateIncident")
    public void getIncident(){
        HashMap<String, String> headerMap=new HashMap<String, String>();
        headerMap.put("Content-Type","application/json");
        headerMap.put("Accept","application/json");
        RestAssured.baseURI="https://dev106543.service-now.com/";
        RestAssured.basePath="/api/now/table/";
        RestAssured.authentication=RestAssured.basic("admin","^qqUL74RdtX/");

        Response response = RestAssured.given().log().all().
                headers(headerMap).
                when().
                get("incident/"+sysId).
                then().log().all().
                statusCode(200).
                extract().response();
        JsonPath jsonPath = response.jsonPath();
        response.then().body("result.short_description", Matchers.is("SoftwareCate_Updated"));
        Assert.assertEquals(jsonPath.get("result.number"),number);



    }
    @Test(dependsOnMethods = {"createIncident","updateIncident","getAllIncident","getIncident"})
    public void deleteIncident(){
        HashMap<String, String> headerMap=new HashMap<String, String>();
        headerMap.put("Content-Type","application/json");
        headerMap.put("Accept","application/json");
        RestAssured.baseURI="https://dev106543.service-now.com/";
        RestAssured.basePath="/api/now/table/";
        RestAssured.authentication=RestAssured.basic("admin","^qqUL74RdtX/");

        Response response = RestAssured.given().log().all().
                headers(headerMap).
                when().
                delete("incident/"+sysId).
                then().log().all().
                statusCode(204).
                extract().response();


    }

}
