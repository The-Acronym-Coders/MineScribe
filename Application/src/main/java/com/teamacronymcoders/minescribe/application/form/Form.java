package com.teamacronymcoders.minescribe.application.form;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.Arrays;
import java.util.List;

public class Form extends GridPane {
    private List<FormElement> elements;

    public Form(FormElement... formElements) {
        this.elements = Arrays.asList(formElements);

        int row = 0;
        for (FormElement formElement : formElements) {
            this.addRow(row, new Label(formElement.getName() + ":"), formElement.getNode());
            row++;
        }

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.getColumnConstraints()
                .addAll(
                        new ColumnConstraints(0, 75, Integer.MAX_VALUE, Priority.SOMETIMES, HPos.RIGHT, true),
                        new ColumnConstraints(200, 200, Integer.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true)
                );
    }

    public Form withSubmit(String text, EventHandler<? super MouseEvent> onClick) {
        Button submitButton = new Button(text);
        submitButton.setOnMouseClicked(onClick);
        this.add(submitButton, 1, elements.size());
        return this;
    }
}
