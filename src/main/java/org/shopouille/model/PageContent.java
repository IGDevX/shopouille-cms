package org.shopouille.model;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;

@Data
@EqualsAndHashCode(callSuper = false)
@MongoEntity(collection = "page-content")
public class PageContent extends PanacheMongoEntityBase {
    public ObjectId id;
    public String type;
    public String title;
    public String content;
}

