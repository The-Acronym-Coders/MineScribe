package com.teamacronymcoders.minescribe.application.form;

import javafx.scene.Node;

public class FormElement {
    private final String name;
    private final Node node;

    public FormElement(String name, Node node) {
        this.name = name;
        this.node = node;
    }

    public String getName() {
        return this.name;
    }

    public Node getNode() {
        return this.node;
    }
}
