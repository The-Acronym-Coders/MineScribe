package com.teamacronymcoders.minescribe.application.module;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class DataPackGeneratorModule extends WorkbenchModule {
    public DataPackGeneratorModule() {
        super("Data Pack Generator", MaterialDesignIcon.ACCOUNT_PLUS);
    }

    @Override
    public Node activate() {
        return new Label("Data Pack Generator");
    }
}
