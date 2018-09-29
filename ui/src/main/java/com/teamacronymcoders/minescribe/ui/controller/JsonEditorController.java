package com.teamacronymcoders.minescribe.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class JsonEditorController {
    @FXML
    private WebView webView;

    @FXML
    private void initialize() {
        WebEngine engine = webView.getEngine();
        engine.load(this.getClass().getResource("/web/JsonEditor.html").toString());
    }
}
