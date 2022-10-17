package org.example;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Rule;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class DelayedStubs {
    @Rule
    public WireMockRule wireMockRule= new WireMockRule(8080);
   @Test
    public void testDelayedStub(){

       StubMapping stubMapping = stubFor(get(urlPathEqualTo("/api/users")).
               willReturn(aResponse().withFixedDelay(5000).
                       proxiedFrom("https://reqres.in")));

       Response response = RestAssured.given().log().all().get("http://localhost:8080/api/users");
       System.out.println(response.statusCode());
       System.out.println(response.getTime());
       response.prettyPrint();

   }
}
