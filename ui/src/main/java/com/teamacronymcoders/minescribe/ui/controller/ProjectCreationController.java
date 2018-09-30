package com.teamacronymcoders.minescribe.ui.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamacronymcoders.minescribe.ui.ProjectSaveState;
import com.teamacronymcoders.minescribe.ui.utils.preferences.ProjectPreferences;
import com.teamacronymcoders.minescribe.ui.utils.preferences.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.prefs.Preferences;

public class ProjectCreationController extends BasicController {
    @FXML
    public TextField projectName;
    @FXML
    public TextField projectLocation;

    public void openLocationChooser(MouseEvent mouseEvent) {

    }

    public void createProject(MouseEvent mouseEvent) {
        UserPreferences userPreferences = UserPreferences.load();
        try {
            File file = new File(projectLocation.getText());
            ProjectPreferences projectPreferences = userPreferences.createProjectPreferences(projectName.getText(), file, true).get();
            File saveStateFile = new File(projectPreferences.folderLocation, projectPreferences.name + ".minescribe.json");
            ProjectSaveState saveState = new ProjectSaveState();
            saveState.setName(projectPreferences.name);
            FileUtils.write(saveStateFile, new ObjectMapper().writeValueAsString(saveState), "UTF-8");
            this.loadPage("ProjectMainMenu");
        } catch (Exception e) {
            showException(e);
        }
    }
}
