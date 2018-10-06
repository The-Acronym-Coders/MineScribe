package com.teamacronymcoders.minescribe.ui.controller.page;

import com.teamacronymcoders.minescribe.ui.controller.ProjectController;
import com.teamacronymcoders.minescribe.ui.controller.partial.ProjectMenuBarController;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class ProjectMainMenuController extends ProjectController {

    @FXML
    public MenuBar menuBar;
    @FXML
    public ProjectMenuBarController menuBarController;

    @Override
    public void setPrimaryStage(Stage stage) {
        super.setPrimaryStage(stage);
        menuBarController.setPrimaryStage(stage);
    }
}
