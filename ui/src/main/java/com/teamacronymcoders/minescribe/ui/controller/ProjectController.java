package com.teamacronymcoders.minescribe.ui.controller;

import com.teamacronymcoders.minescribe.ui.utils.preferences.ProjectPreferences;
import com.teamacronymcoders.minescribe.ui.utils.preferences.UserPreferences;

public class ProjectController extends BasicController {
    private final UserPreferences userPreferences;
    private final ProjectPreferences activeProject;

    public ProjectController() {
        this.userPreferences = UserPreferences.load();
        this.activeProject = this.userPreferences.getActiveProject();
    }

    public UserPreferences getUserPreferences() {
        return this.userPreferences;
    }

    public ProjectPreferences getActiveProject() {
        return this.activeProject;
    }
}
