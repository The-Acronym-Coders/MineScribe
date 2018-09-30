package com.teamacronymcoders.minescribe.ui.utils.preferences;

import com.google.common.base.Strings;
import com.teamacronymcoders.minescribe.ui.utils.functional.Try;
import javafx.scene.control.Alert;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class UserPreferences {
    private final Preferences minescribeNode;
    private final Preferences projectsNode;

    private UserPreferences(Preferences userNode) {
        this.minescribeNode = userNode.node("minescribe");
        this.projectsNode = minescribeNode.node("projects");
    }

    public ProjectPreferences getActiveProject() {
        return this.getProjectPreferences().stream()
                .filter(projectPreference -> projectPreference.active)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No Active Project"));
    }

    public List<ProjectPreferences> getProjectPreferences() {
        try {
            return Arrays.stream(projectsNode.childrenNames())
                    .map(projectsNode::node)
                    .map(ProjectPreferences::load)
                    .filter(Try::isSuccess)
                    .map(Try::get)
                    .collect(Collectors.toList());

        } catch (BackingStoreException e) {
            throw new RuntimeException("Failed to Collect Recent Projects", e);
        }
    }

    public Try<ProjectPreferences> createProjectPreferences(String name, File folderLocation, boolean active) {
        Preferences preferences = this.projectsNode.node(name);

        if (!Strings.isNullOrEmpty(name) && folderLocation.exists() && folderLocation.isDirectory()) {
            preferences.put("name", name);
            preferences.put("filePath", folderLocation.toString());
            preferences.putBoolean("active", active);

            try {
                preferences.flush();
                return ProjectPreferences.load(preferences);
            } catch (BackingStoreException e) {
                return Try.failure(new RuntimeException("Failed to create Project Preferences"));
            }
        } else {
            return Try.failure(new IllegalArgumentException("File Path is Not Valid"));
        }
    }

    public static UserPreferences load() {
        return new UserPreferences(Preferences.userRoot());
    }

    public void setActiveProject(ProjectPreferences projectPreferences) {
        try {
            for (String nodeName: projectsNode.childrenNames()) {
                Preferences currentNode = projectsNode.node(nodeName);
                currentNode.putBoolean("active", projectPreferences.name.equals(nodeName));
                currentNode.flush();
            }
        } catch (BackingStoreException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to Alter Project Active State").showAndWait();
        }
    }
}
