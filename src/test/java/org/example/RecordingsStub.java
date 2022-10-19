package org.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.recording.SnapshotRecordResult;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


public class RecordingsStub {
@Test
    public static void recordingStub(){

        WireMockServer wireMockServer= new WireMockServer(wireMockConfig().port(8080));
        wireMockServer.start();


    wireMockServer.startRecording("https://reqres.in");

        RestAssured.baseURI="http://localhost:8080";

        RestAssured.given().log().all().get("/api/users");
        RestAssured.given().log().all().get("/api/users/1");

       SnapshotRecordResult snapshotRecordResult = wireMockServer.stopRecording();
       List<StubMapping> stubMappings = snapshotRecordResult.getStubMappings();


    }
}
