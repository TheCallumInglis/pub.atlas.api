package com.calluminglis.pubatlas.resource;

import com.calluminglis.pubatlas.domain.Pub;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class PubResourceTest {
    Long pubId;

    @BeforeEach
    @Transactional
    void resetAndSeed() {
        Pub.deleteAll();

        Pub p = new Pub();
        p.name = "Test Pub";
        p.latitude = null;
        p.longitude = null;
        p.persist();

        pubId = p.id;
    }

    @Test
    void geocodeMissing_updatesCoords() {
        given()
                .header("X-API-Key", "test-api-key")
                .when().post("/pubs/geocode-missing?limit=1")
                .then()
                .statusCode(200)
                .body("processed", is(1));

        Pub updated = Pub.findById(pubId);
        assertNotNull(updated.latitude);
        assertNotNull(updated.longitude);
    }
}
