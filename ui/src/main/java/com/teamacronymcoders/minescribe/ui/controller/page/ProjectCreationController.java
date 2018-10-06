package com.teamacronymcoders.minescribe.ui.controller.page;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamacronymcoders.minescribe.ui.ProjectSaveState;
import com.teamacronymcoders.minescribe.ui.controller.BasicController;
import com.teamacronymcoders.minescribe.ui.utils.preferences.ProjectPreferences;
import com.teamacronymcoders.minescribe.ui.utils.preferences.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.prefs.Preferences;

public class ProjectCreationController extends BasicController {
    @FXML
    public TextField projectName;
    @FXML
    public TextField projectLocation;

    public void openLocationChooser() {
        DirectoryChooser projectLocationChooser = new DirectoryChooser();
        projectLocationChooser.setTitle("Select Project Directory");
        projectLocationChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = projectLocationChooser.showDialog(this.getPrimaryStage());
        if (file != null) {
            projectLocation.setText(file.getAbsolutePath());
        }
    }

    public void createProject() {
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
