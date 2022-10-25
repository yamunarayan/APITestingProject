package org.example;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class ProxyResponseTemplate {
    @Rule
    public WireMockRule wiremock = new WireMockRule(wireMockConfig().port(8082).
            extensions(new ResponseTemplateTransformer(false)));
@Test
    public void proxyStub(){
        stubFor(get(urlPathEqualTo("/api/users")).
                willReturn(aResponse().
                        proxiedFrom("{{request.headers.url}}").
                        withStatus(200).withTransformers("response-template")));

        verify(lessThan(5),getRequestedFor(urlPathEqualTo("/api/users")));


        RestAssured.baseURI="http://localhost:8082";

        RestAssured.given().log().all().header("url","https://reqres.in").
                get("/api/users").then().log().all();
    }
}
