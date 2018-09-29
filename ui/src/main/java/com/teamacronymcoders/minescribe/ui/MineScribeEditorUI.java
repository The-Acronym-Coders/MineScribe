package com.teamacronymcoders.minescribe.ui;

import com.teamacronymcoders.minescribe.ui.controller.BasicController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MineScribeEditorUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/pages/ProjectSelection.fxml"));
        primaryStage.setTitle("MineScribe");
        primaryStage.setScene(new Scene(loader.load(), 600, 400));
        BasicController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.show();
    }
}
