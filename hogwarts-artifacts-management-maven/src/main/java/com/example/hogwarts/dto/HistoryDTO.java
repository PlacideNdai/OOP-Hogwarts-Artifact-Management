package com.example.hogwarts.dto;

import java.time.LocalDateTime;

public class HistoryDTO {
    private String action;
    private String fromWizard;
    private String toWizard;
    private int artifactId;
    private LocalDateTime timestamp;

    public HistoryDTO() {}

    public HistoryDTO(String action, String fromWizard, String toWizard, int artifactId,
            LocalDateTime timestamp) {
        this.action = action;
        this.fromWizard = fromWizard;
        this.toWizard = toWizard;
        this.artifactId = artifactId;
        this.timestamp = timestamp;
    }

    public String getAction() {
        return action;
    }

    public String getFromWizard() {
        return fromWizard;
    }

    public String getToWizard() {
        return toWizard;
    }

    public int getArtifact() {
        return artifactId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setFromWizard(String fromWizard) {
        this.fromWizard = fromWizard;
    }

    public void setToWizard(String toWizard) {
        this.toWizard = toWizard;
    }

    public void setArtifact(int artifactId) {
        this.artifactId = artifactId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "HistoryDTO{" +
                "action='" + action + '\'' +
                ", fromWizard='" + fromWizard + '\'' +
                ", toWizard='" + toWizard + '\'' +
                ", artifactId=" + artifactId +
                ", timestamp=" + timestamp +
                '}';
    }
}
