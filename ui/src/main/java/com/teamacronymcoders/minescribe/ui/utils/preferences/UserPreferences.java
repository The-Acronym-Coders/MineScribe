package com.teamacronymcoders.minescribe.ui.utils.preferences;

import com.google.common.base.Strings;
import com.teamacronymcoders.minescribe.ui.utils.functional.Try;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class UserPreferences {
    private final Preferences minescribeNode;
    private final Preferences projectsNode;

    public UserPreferences(Preferences userNode) {
        this.minescribeNode = userNode.node("minescribe");
        this.projectsNode = minescribeNode.node("projects");
    }

    public Try<List<ProjectPreferences>> getProjectPreferences() {
        try {
            return Try.sequence(Arrays.stream(projectsNode.childrenNames())
                    .map(projectsNode::node)
                    .map(ProjectPreferences::load)
                    .collect(Collectors.toList()));
        } catch (BackingStoreException e) {
            return Try.failure(new RuntimeException("Failed to Collect Recent Projects", e));
        }
    }

    public Try<ProjectPreferences> createProjectPreferences(String name, File folderLocation) {
        Preferences preferences = this.projectsNode.node(name);

        if (!Strings.isNullOrEmpty(name) && folderLocation.exists() && folderLocation.isDirectory()) {
            preferences.put("name", name);
            preferences.put("filePath", folderLocation.toString());

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
}
