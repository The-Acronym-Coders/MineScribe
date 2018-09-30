package com.teamacronymcoders.minescribe.ui.controller;

import javafx.scene.input.MouseEvent;

public class ProjectMainMenuController extends ProjectController {
    public void openJsonEditor(MouseEvent mouseEvent) {
        this.loadPage("JsonEditor");
    }
}
