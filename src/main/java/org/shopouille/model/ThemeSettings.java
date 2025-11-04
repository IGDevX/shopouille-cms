package org.shopouille.model;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@MongoEntity(collection = "theme-settings")
public class ThemeSettings extends PanacheMongoEntityBase {
    public ObjectId id;
    public String primaryColor;
    public String secondaryColor;

    /**
     * Find first document in the collection (no website id for now to filter)
     */
    public static ThemeSettings findTheOne() {
        return find("{}").firstResult();
    }
}

