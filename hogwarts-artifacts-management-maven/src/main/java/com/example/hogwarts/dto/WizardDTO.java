package com.example.hogwarts.dto;

import java.util.List;

public class WizardDTO {
    private int id;
    private String name;
    private List<ArtifactDTO> artifacts;

    public WizardDTO(int id, String name, List<ArtifactDTO> artifacts) {
        this.id = id;
        this.name = name;
        this.artifacts = artifacts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ArtifactDTO> getArtifacts() {
        return artifacts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtifacts(List<ArtifactDTO> artifacts) {
        this.artifacts = artifacts;
    }

    @Override
    public String toString() {
        return "WizardDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artifacts=" + artifacts +
                '}';
    }
}
