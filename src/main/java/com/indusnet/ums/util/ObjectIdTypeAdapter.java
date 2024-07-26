package com.indusnet.ums.util;

import com.google.gson.*;
import org.bson.types.ObjectId;
import java.lang.reflect.Type;


public class ObjectIdTypeAdapter implements JsonDeserializer<ObjectId>, JsonSerializer<ObjectId> {
    @Override
    public ObjectId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new ObjectId(json.getAsString());
    }

    @Override
    public JsonElement serialize(ObjectId src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toHexString());
    }
}