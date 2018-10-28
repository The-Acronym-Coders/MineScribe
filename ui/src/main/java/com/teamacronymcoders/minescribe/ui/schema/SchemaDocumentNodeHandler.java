package com.teamacronymcoders.minescribe.ui.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.teamacronymcoders.minescribe.ui.utils.functional.Try;
import com.teamacronymcoders.minescribe.ui.utils.json.JsonGrabbers;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SchemaDocumentNodeHandler {
    private final ObjectMapper objectMapper;

    public SchemaDocumentNodeHandler() {
        objectMapper = new ObjectMapper();
    }

    public Try<SchemaDocument> readFromFile(File file) {
        try {
            JsonNode root = objectMapper.readTree(file);
            String id = JsonGrabbers.getObjectOrThrow(root, "$id", JsonNode::asText);
            String schema = JsonGrabbers.getObjectOrThrow(root, "$schema", JsonNode::asText);
            String description = JsonGrabbers.getObjectOrNull(root, "description", JsonNode::asText);
            String type = JsonGrabbers.getObjectOrThrow(root, "type", JsonNode::asText);

            String[] required;
            if (root.get("required").isArray()) {
                required = JsonGrabbers.tryGetObject(root, "required", jsonNode -> {
                    Iterator<JsonNode> iterator = jsonNode.iterator();
                    List<String> values = Lists.newArrayList();
                    while (iterator.hasNext()) {
                        values.add(iterator.next().asText());
                    }
                    return values.toArray(new String[0]);
                }).orElseGet(() -> new String[0]);
            } else {
                required = JsonGrabbers.tryGetObject(root, "required", JsonNode::asText)
                        .map(value -> new String[]{value})
                        .orElseGet(() -> new String[0]);
            }
            Map<String, SchemaProperty> properties = JsonGrabbers.tryGetObject(root, "properties", this::handleProperties)
                    .orElseGet(Maps::newHashMap);

            return Try.success(new SchemaDocument(id, schema, description, type, required, properties));
        } catch (IOException e) {
            return Try.failure(new IllegalStateException(e));
        }
    }

    private Map<String, SchemaProperty> handleProperties(JsonNode properties) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(properties.fields(), 0), false)
                .map(this::handleProperty)
                .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
    }

    private Pair<String, SchemaProperty> handleProperty(Map.Entry<String, JsonNode> property) {
        SchemaProperty schemaProperty = null;

        return Pair.of(property.getKey(), schemaProperty);
    }
}
