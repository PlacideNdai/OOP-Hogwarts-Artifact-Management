package com.example.hogwarts.dto;

public class ArtifactDTO {
    private int id;
    private String name;
    private String description;
    private int ownerId;
    private int quality;

    public ArtifactDTO() {}
    
    public ArtifactDTO(int id, int ownerId, String name, String description, int quality) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.quality = quality;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getOwner() {
        return ownerId;
    }

    public int getQuality() {
        return quality;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "WizardDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", owner='" + ownerId + '\'' +
                ", quality=" + quality +
                '}';
    }
}
