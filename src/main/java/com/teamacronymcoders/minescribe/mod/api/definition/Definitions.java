package com.teamacronymcoders.minescribe.mod.api.definition;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Definitions {
    @SerializedName("$schema")
    public final String schema;
    @SerializedName("$id")
    public final String id;
    @SerializedName("definitions")
    public final Map<String, Definition> definitions;

    public Definitions(String id, Map<String, Definition> definitions) {
        this.schema = "http://json-schema.org/schema#";
        this.id = id;
        this.definitions = definitions;
    }
}
