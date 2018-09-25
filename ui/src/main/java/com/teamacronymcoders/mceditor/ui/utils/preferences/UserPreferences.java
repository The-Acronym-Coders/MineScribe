package com.teamacronymcoders.mceditor.ui.utils.preferences;

import com.teamacronymcoders.mceditor.ui.utils.functional.Try;

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
}
