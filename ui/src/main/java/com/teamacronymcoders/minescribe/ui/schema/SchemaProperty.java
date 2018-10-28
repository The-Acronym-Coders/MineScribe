package com.teamacronymcoders.minescribe.ui.schema;

import com.teamacronymcoders.minescribe.ui.control.FormPane;
import javafx.scene.layout.GridPane;

public abstract class SchemaProperty {
    public final SchemaType type;

    protected SchemaProperty(SchemaType type) {
        this.type = type;
    }

    public abstract void addToForm(FormPane formPane);
}
