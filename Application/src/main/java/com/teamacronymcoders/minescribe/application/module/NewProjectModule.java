package com.teamacronymcoders.minescribe.application.module;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.teamacronymcoders.minescribe.application.model.Project;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class NewProjectModule extends WorkbenchModule {
    private Consumer<Project> handleProject;

    public NewProjectModule() {
        super("New Project", FontAwesomeIcon.ADDRESS_BOOK);
    }

    @Override
    public Node activate() {
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPrefSize(600, 400);
        gridPane.setMaxSize(800, 600);
        gridPane.getColumnConstraints()
                .addAll(
                        new ColumnConstraints(0, 75, Integer.MAX_VALUE, Priority.SOMETIMES, HPos.RIGHT, true),
                        new ColumnConstraints(200, 200, Integer.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true)
                );
        gridPane.add(new Label("New Project Creation"), 1, 0);
        gridPane.add(new Label("Name:"), 0, 1);
        TextField name = new TextField();
        gridPane.add(name, 1, 1);
        gridPane.add(new Label("Folder:"), 0, 2);
        TextField location = new TextField();
        location.setOnMouseClicked(event -> {
            DirectoryChooser projectLocationChooser = new DirectoryChooser();
            projectLocationChooser.setTitle("Select Project Directory");
            projectLocationChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File file = projectLocationChooser.showDialog(null);
            if (file != null) {
                location.setText(file.getAbsolutePath());
            }
        });
        gridPane.add(location, 1, 2);
        Button createProject = new Button("Create Project");
        createProject.setOnMouseClicked(event -> {
            boolean valid = true;
            if (name.getText().isEmpty()) {
                valid = false;
            }
            if (location.getText().isEmpty()) {
                valid = false;
            } else {
                File locationFile = new File(location.getText());
                if (!locationFile.exists() || !locationFile.isDirectory()) {
                    valid = false;
                }
            }

            if (valid) {
                Project project = new Project();
                project.setName(name.getText());
                project.setProjectLocation(Paths.get(location.getText()));
                handleProject.accept(project);
            } else {
                this.getWorkbench().showInformationDialog("Project Create Failed", "", null);
            }
        });
        gridPane.add(createProject, 1, 3);
        borderPane.setCenter(gridPane);

        return gridPane;
    }

    public void setHandleProject(Consumer<Project> handleProject) {
        this.handleProject = handleProject;
    }
}
