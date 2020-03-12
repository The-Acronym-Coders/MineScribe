package com.teamacronymcoders.minescribe.application.module;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class JsonEditorModule extends WorkbenchModule {
    public JsonEditorModule(String name) {
        super(name, MaterialDesignIcon.BOOK_OPEN);
    }

    @Override
    public Node activate() {
        return new Label(getName());
    }
}
