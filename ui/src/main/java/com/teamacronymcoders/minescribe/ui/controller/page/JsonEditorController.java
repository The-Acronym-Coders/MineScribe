package com.teamacronymcoders.minescribe.ui.controller.page;

import com.teamacronymcoders.minescribe.ui.controller.ProjectController;
import com.teamacronymcoders.minescribe.ui.utils.preferences.UserPreferences;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.IOException;

public class JsonEditorController extends ProjectController {

    public TreeItem<String> resourceTreeItem;
    public TreeView resourceTreeView;

    @FXML
    private void initialize() {
        UserPreferences userPreferences = UserPreferences.load();
        resourceTreeItem.setValue("Resources");
        File resourceFolder = userPreferences.getResourceFolderLocation();
        addResourceChildren(resourceTreeItem, resourceFolder);
    }

    private void addResourceChildren(TreeItem<String> treeItem, File folder) {
        if (folder.isDirectory()) {
            String[] children = folder.list();
            if (children != null) {
                for (String childName: children) {
                    TreeItem<String> childTreeItem = new TreeItem<>(childName);
                    File child = new File(folder, childName);
                    if (child.isDirectory()) {
                        addResourceChildren(childTreeItem, child);
                    }
                    treeItem.getChildren().add(childTreeItem);
                }
            }
        }
    }
}
