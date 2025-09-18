package com.example.hogwarts.history;

import java.time.LocalDateTime;

import com.example.hogwarts.model.Artifact;
import com.example.hogwarts.model.Wizard;

public class History {
    private final LocalDateTime timestamp;
    private final String action;
    private final Wizard fromWizard;
    private final Wizard toWizard;
    private final Artifact artifact;
    public final static String ASSIGN = "ASSIGN";
    public final static String UNASSIGN = "UNASSIGN";

    public History(Wizard fromWizard, Wizard toWizard, Artifact artifact, String action) {
        this.timestamp = LocalDateTime.now();
        this.fromWizard = fromWizard;
        this.toWizard = toWizard;
        this.artifact = artifact;
        this.action = action;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Wizard getFromWizard() {
        return fromWizard;
    }

    public Wizard getToWizard() {
        return toWizard;
    }

    public String getAction() {
        return action;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    @Override
    public String toString() {
        return "History{" +
                "timestamp=" + timestamp +
                ", fromWizard='" + fromWizard + '\'' +
                ", toWizard='" + toWizard + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
