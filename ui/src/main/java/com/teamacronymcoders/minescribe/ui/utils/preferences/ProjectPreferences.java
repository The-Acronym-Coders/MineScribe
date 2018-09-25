package com.teamacronymcoders.minescribe.ui.utils.preferences;

import com.teamacronymcoders.minescribe.ui.utils.functional.Try;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.prefs.Preferences;

public class ProjectPreferences {
    public final String name;
    public final File folderLocation;

    private ProjectPreferences(String name, File folderLocation) {
        this.name = name;
        this.folderLocation = folderLocation;
    }

    public static Try<ProjectPreferences> create(String name, File folderLocation) {
        if (StringUtils.isEmpty(name)) {
            return Try.failure(new IllegalArgumentException("Name is Empty"));
        } else if (!folderLocation.exists() && !folderLocation.isDirectory()) {
            return Try.failure(new IllegalArgumentException("File Path Provided is not a Directory"));
        } else {
            return Try.success(new ProjectPreferences(name, folderLocation));
        }
    }

    public static Try<ProjectPreferences> load(Preferences preferences) {
        String name = preferences.get("name", "Unknown");
        String pathName = preferences.get("filePath", "");
        File file = new File(pathName);
        return create(name, file);
    }
}
