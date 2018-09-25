package com.teamacronymcoders.mceditor.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class ProjectSelectionController implements Initializable {
    @FXML
    private VBox recentProjects;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Preferences.userRoot().
        for (int i = 0; i < 6; i++) {
            Button button = new Button("Project " + i);
            button.setPrefHeight(50);
            button.setPrefWidth(200);
            button.getStyleClass().add("recentProject");
            recentProjects.getChildren().add(button);
        }
    }

    @FXML
    public void createNewProject(MouseEvent mouseEvent) {

    }
}
