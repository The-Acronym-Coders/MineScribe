package com.teamacronymcoders.minescribe.ui.controller;

import javafx.scene.input.MouseEvent;

public class ProjectMainMenuController extends BasicController {
    public void openJsonEditor(MouseEvent mouseEvent) {
        this.loadPage("JsonEditor");
    }
}
