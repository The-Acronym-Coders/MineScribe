package com.teamacronymcoders.minescribe.ui.controller;

import com.teamacronymcoders.minescribe.ui.utils.preferences.ProjectPreferences;
import com.teamacronymcoders.minescribe.ui.utils.preferences.UserPreferences;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class ProjectSelectionController extends BasicController implements Initializable {
    @FXML
    private VBox recentProjects;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserPreferences userPreferences = new UserPreferences(Preferences.userRoot());
        try {
            List<ProjectPreferences> projectPreferencesList = userPreferences.getProjectPreferences().get();
            if (!projectPreferencesList.isEmpty()) {
                projectPreferencesList.forEach(this::createAndAddButton);
            } else {
                recentProjects.getChildren().add(new Text("No Recent Projects"));
            }
        } catch (Exception exception) {
            showException(exception);
        }
    }

    private void createAndAddButton(ProjectPreferences projectPreferences) {
        Button button = new Button(projectPreferences.name);
        button.setOnMouseClicked(event -> loadProject(projectPreferences));
        button.setPrefHeight(50);
        button.setPrefWidth(200);
        button.getStyleClass().add("recentProject");
        recentProjects.getChildren().add(button);
    }

    private void loadProject(ProjectPreferences projectPreferences) {

    }

    @FXML
    public void createNewProject(MouseEvent mouseEvent) {
        this.loadPage("ProjectCreation");
    }
}
