package com.teamacronymcoders.minescribe.ui.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.teamacronymcoders.minescribe.ui.control.FormPane;
import com.teamacronymcoders.minescribe.ui.utils.json.JsonGrabbers;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class SchemaStringType extends SchemaProperty {
    public final Integer maxLength;
    public final Integer minLength;
    public final String pattern;
    public final String defaultValue;

    private SchemaStringType(Integer maxLength, Integer minLength, String pattern, String defaultValue) {
        super(SchemaType.STRING);
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.pattern = pattern;
        this.defaultValue = defaultValue;
    }

    public static SchemaStringType parse(JsonNode jsonNode) {
        return new SchemaStringType(JsonGrabbers.getObjectOrNull(jsonNode, "maxLength", JsonNode::asInt),
                JsonGrabbers.getObjectOrNull(jsonNode, "minLength", JsonNode::asInt),
                JsonGrabbers.getObjectOrNull(jsonNode, "pattern", JsonNode::toString),
                JsonGrabbers.getObjectOrNull(jsonNode, "default", JsonNode::toString));
    }

    @Override
    public void addToForm(FormPane formPane) {
        TextInputControl fieldInput = new TextField();

    }
}
