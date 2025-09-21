
package com.example.hogwarts.model;

import java.time.LocalDateTime;

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

    public History(Wizard fromWizard, Wizard toWizard, Artifact artifact, String action, LocalDateTime timestamp) {
        this.timestamp = timestamp;
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
        String from = (fromWizard != null) ? fromWizard.getName() : "Unassigned";
        String to = (toWizard != null) ? toWizard.getName() : "Unassigned";

        return "Action: " + action + "\n" +
                "From: " + from + "\n" +
                "To: " + to + '\n' +
                "Artifact: " + artifact.getName() + "\n\n";
    }
}
