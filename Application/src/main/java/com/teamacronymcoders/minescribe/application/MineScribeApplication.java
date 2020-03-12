package com.teamacronymcoders.minescribe.application;

import com.dlsc.workbenchfx.Workbench;
import com.teamacronymcoders.minescribe.application.module.DataPackGeneratorModule;
import com.teamacronymcoders.minescribe.application.module.JsonEditorModule;
import com.teamacronymcoders.minescribe.application.module.NewProjectModule;
import com.teamacronymcoders.minescribe.application.module.ProjectSettingsModule;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MineScribeApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        NewProjectModule newProjectModule = new NewProjectModule();
        Workbench projectSelector = Workbench.builder(
                newProjectModule
        ).build();
        Scene scene = new Scene(projectSelector);
        newProjectModule.setHandleProject(project -> {
            primaryStage.setTitle("MineScribe: " + project.getName());
            scene.setRoot(Workbench.builder(
                    new DataPackGeneratorModule(),
                    new JsonEditorModule("Data Pack"),
                    new JsonEditorModule("Resource Pack"),
                    new ProjectSettingsModule(project)
            ).build());
        });
        primaryStage.setTitle("MineScribe");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
