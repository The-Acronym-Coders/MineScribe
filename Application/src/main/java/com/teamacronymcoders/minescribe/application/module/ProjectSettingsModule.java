package com.teamacronymcoders.minescribe.application.module;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.teamacronymcoders.minescribe.application.form.Form;
import com.teamacronymcoders.minescribe.application.form.FormElement;
import com.teamacronymcoders.minescribe.application.model.Project;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class ProjectSettingsModule extends WorkbenchModule {
    private final Project project;

    public ProjectSettingsModule(Project project) {
        super("Settings", MaterialDesignIcon.SETTINGS);
        this.project = project;
    }

    @Override
    public Node activate() {
        return new Form(
                new FormElement("Name", new TextField(project.getName())),
                new FormElement("Location", new TextField(project.getProjectLocation().toString()))
        ).withSubmit("Update Settings", mouseEvent -> {
            project.setProjectLocation();
        });
    }
}
