package org.shopouille.model;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

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
