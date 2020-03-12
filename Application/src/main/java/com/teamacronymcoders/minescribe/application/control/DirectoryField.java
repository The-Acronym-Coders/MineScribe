package com.teamacronymcoders.minescribe.application.control;

import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryField extends TextField {
    public DirectoryField() {
        this(Paths.get(System.getProperty("user.home")));
    }

    public DirectoryField(Path priorFile) {
        setOnMouseClicked(event -> {
            DirectoryChooser projectLocationChooser = new DirectoryChooser();
            projectLocationChooser.setTitle("Select Project Directory");
            projectLocationChooser.setInitialDirectory(priorFile.toFile());
            File file = projectLocationChooser.showDialog(null);
            if (file != null) {
                setText(file.getAbsolutePath());
            }
        });
    }
}
