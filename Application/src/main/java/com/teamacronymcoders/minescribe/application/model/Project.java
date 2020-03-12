package com.teamacronymcoders.minescribe.application.model;

import com.google.common.collect.Lists;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class Project {
    private String name;
    private Path projectLocation;
    private List<Path> dataPackLocations = Lists.newArrayList();
    private List<Path> resourcePackLocations = Lists.newArrayList();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Path getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(Path projectLocation) {
        this.projectLocation = projectLocation;
    }

    public List<Path> getDataPackLocations() {
        return dataPackLocations;
    }

    public void setDataPackLocations(List<Path> dataPackLocations) {
        this.dataPackLocations = dataPackLocations;
    }

    public List<Path> getResourcePackLocations() {
        return resourcePackLocations;
    }

    public void setResourcePackLocations(List<Path> resourcePackLocations) {
        this.resourcePackLocations = resourcePackLocations;
    }
}
