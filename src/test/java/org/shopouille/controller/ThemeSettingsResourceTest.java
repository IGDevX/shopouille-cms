package org.shopouille.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shopouille.model.ThemeSettings;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestProfile(MemoryMongoTestProfile.class)
public class ThemeSettingsResourceTest {

    @BeforeEach
    public void cleanup() {
        ThemeSettings.deleteAll();
    }

    @Test
    public void testGetThemeSettings_NotFound() {
        given()
                .when()
                .get("/theme-settings")
                .then()
                .statusCode(404)
                .body("message", equalTo("Theme settings not found"));
    }

    @Test
    public void testGetThemeSettings_Found() {
        ThemeSettings settings = new ThemeSettings();
        settings.setPrimaryColor("#111111");
        settings.setSecondaryColor("#222222");
        settings.persist();

        given()
                .when()
                .get("/theme-settings")
                .then()
                .statusCode(200)
                .body("primaryColor", equalTo("#111111"))
                .body("secondaryColor", equalTo("#222222"));
    }

    @Test
    public void testUpdateThemeSettings_CreateNew() {
        String body = """
            {
                "primaryColor": "#FF0000",
                "secondaryColor": "#00FF00"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch("/theme-settings")
                .then()
                .statusCode(200)
                .body("primaryColor", equalTo("#FF0000"))
                .body("secondaryColor", equalTo("#00FF00"));

        // Vérifier en DB
        ThemeSettings stored = ThemeSettings.findTheOne();
        assert stored != null;
        assert stored.getPrimaryColor().equals("#FF0000");
    }

    @Test
    public void testUpdateThemeSettings_UpdateExisting() {
        ThemeSettings settings = new ThemeSettings();
        settings.setPrimaryColor("#111111");
        settings.setSecondaryColor("#222222");
        settings.persist();

        String body = """
            {
                "primaryColor": "#AAAAAA",
                "secondaryColor": "#BBBBBB"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch("/theme-settings")
                .then()
                .statusCode(200)
                .body("primaryColor", equalTo("#AAAAAA"))
                .body("secondaryColor", equalTo("#BBBBBB"));

        // Vérification DB
        ThemeSettings stored = ThemeSettings.findTheOne();
        assert stored.getPrimaryColor().equals("#AAAAAA");
    }
}
