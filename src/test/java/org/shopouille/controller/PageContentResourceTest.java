package org.shopouille.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.RestAssured;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shopouille.model.PageContent;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestProfile(MemoryMongoTestProfile.class)
public class PageContentResourceTest {

    ObjectId existingId;

    @BeforeEach
    void setup() {
        PageContent.deleteAll();
        PageContent page = new PageContent();
        page.setContent("Initial content");
        page.persist();
        existingId = page.id;
    }

    @Test
    void testGetAllPages() {
        given()
                .when().get("/page-content")
                .then()
                .statusCode(200)
                .body("$", hasSize(1))
                .body("[0].content", equalTo("Initial content"));
    }

    @Test
    void testGetPageByIdSuccess() {
        given()
                .when().get("/page-content/" + existingId)
                .then()
                .statusCode(200)
                .body("content", equalTo("Initial content"));
    }

    @Test
    void testGetPageByIdInvalidObjectId() {
        given()
                .when().get("/page-content/1234")
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid ObjectId format"));
    }

    @Test
    void testGetPageByIdNotFound() {
        given()
                .when().get("/page-content/" + new ObjectId())
                .then()
                .statusCode(404)
                .body("message", equalTo("Page not found"));
    }

    @Test
    void testUpdatePageSuccess() {
        given()
                .body("{\"content\": \"Updated content\"}")
                .header("Content-Type", "application/json")
                .when().patch("/page-content/" + existingId)
                .then()
                .statusCode(200)
                .body("content", equalTo("Updated content"));
    }

    @Test
    void testUpdatePageInvalidObjectId() {
        given()
                .body("{\"content\": \"Err\"}")
                .header("Content-Type", "application/json")
                .when().patch("/page-content/invalid-id")
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid ObjectId format"));
    }

    @Test
    void testUpdatePageNotFound() {
        given()
                .body("{\"content\": \"Something\"}")
                .header("Content-Type", "application/json")
                .when().patch("/page-content/" + new ObjectId())
                .then()
                .statusCode(404)
                .body("message", equalTo("Page not found"));
    }
}
