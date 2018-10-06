package com.teamacronymcoders.minescribe.ui.schema;

import java.util.Map;

public class SchemaDocument {
    public final String id;
    public final String schema;
    public final String description;
    public final String type;
    public final String[] required;
    public final Map<String, SchemaProperty> properties;

    public SchemaDocument(String id, String schema, String description, String type, String[] required, Map<String, SchemaProperty> properties) {
        this.id = id;
        this.schema = schema;
        this.description = description;
        this.type = type;
        this.required = required;
        this.properties = properties;
    }
}
