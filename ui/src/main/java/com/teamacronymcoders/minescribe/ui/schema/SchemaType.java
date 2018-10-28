package com.teamacronymcoders.minescribe.ui.schema;

import java.util.Locale;

public enum SchemaType {
    BOOLEAN,
    OBJECT,
    ARRAY,
    NUMBER,
    STRING,
    NULL;

    public String getName() {
        return this.toString().toLowerCase(Locale.ENGLISH);
    }
}
