package com.teamacronymcoders.minescribe.mod.api.definition;

import com.google.gson.annotations.SerializedName;

public class StringArrayDefinition extends Definition {
    @SerializedName("type")
    private final String type = "string";
    @SerializedName("enum")
    private final String[] values;

    public StringArrayDefinition(String[] values) {
        this.values = values;
    }
}
