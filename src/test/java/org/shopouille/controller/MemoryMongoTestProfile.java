package org.shopouille.controller;

import io.quarkus.test.junit.TestProfile;
import io.quarkus.test.junit.QuarkusTestProfile;
import java.util.Map;

public class MemoryMongoTestProfile implements QuarkusTestProfile {
    @Override
    public Map<String, String> getConfigOverrides() {
        return Map.of(
                "quarkus.mongodb.devservices.enabled", "true",
                "quarkus.mongodb.connection-string", "mongodb://localhost:27017/test",
                "quarkus.mongodb.database", "test"
        );
    }
}
