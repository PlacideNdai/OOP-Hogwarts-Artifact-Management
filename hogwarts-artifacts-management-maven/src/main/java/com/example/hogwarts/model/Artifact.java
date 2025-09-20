package com.example.hogwarts.model;

import java.util.Objects;

public class Artifact {
    private int id;
    private String name;
    private String description;
    private Wizard owner; // can be null
    private int quality;
    public final int MINUS_QUALITY = 5;
    public final int MINMAL_TRADE_QUALITY = 10;

    public Artifact(String name, String description, int quality) {
        this.name = Objects.requireNonNullElse(name, "name must not be null");
        this.description = Objects.requireNonNullElse(description, "description must not be null");
        this.owner = null;
        setQualityClamped(quality);
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

    public Wizard getOwner() {
        return owner;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNullElse(name, "name must not be null");
    }

    public void setDescription(String description) {
        this.description = Objects.requireNonNullElse(description, "description must not be null");
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getQuality() {
        return quality;
    }

    void setOwner(Wizard owner) {
        this.owner = owner;
    } // package-private to restrict access

    public void setMinusQuality(Artifact artifact) {
        if (artifact.getQuality() > 0) {
            this.quality = this.quality - this.MINUS_QUALITY;
        }
    }

    void setQualityClamped(int quality) {
        this.quality = Math.max(0, Math.min(100, quality));
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }

}
