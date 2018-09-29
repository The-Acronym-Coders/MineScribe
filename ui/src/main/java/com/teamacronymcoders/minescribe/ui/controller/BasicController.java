package com.teamacronymcoders.minescribe.ui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class BasicController {
    private Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    protected void loadPage(String pageName) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(String.format("/pages/%s.fxml", pageName)));

        try {
            primaryStage.setScene(new Scene(loader.load(), primaryStage.getWidth(), primaryStage.getHeight()));
            BasicController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
        } catch (IOException e) {
            showException(e);
        }
    }

    protected void showException(Exception exception) {
        String message = exception.getCause() != null ? exception.getCause().getMessage(): exception.getMessage();
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }
}
