package com.calluminglis.pubatlas.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class HealthCheckResourceTest {
    @Test
    void testHealthCheckEndpoint() {
        given()
          .when().get("/healthcheck")
          .then()
             .statusCode(200)
             .body("status", is("ok"));
    }

}