package com.example.hogwarts.dto;

import com.example.hogwarts.model.Artifact;
import com.example.hogwarts.model.History;
import com.example.hogwarts.model.Wizard;

public class FromDtoConverter {
    
    public static Wizard toWizard(WizardDTO wizardDTO) {
        Wizard wizard = new Wizard(wizardDTO.getName());
        wizard.setId(wizardDTO.getId());

        return wizard;
    }

    public static Artifact toArtifact(ArtifactDTO artifactDTO) {
        Artifact artifact = new Artifact(artifactDTO.getName(), artifactDTO.getDescription(), artifactDTO.getQuality());
        artifact.setId(artifactDTO.getId());
        return artifact;
    }

    public static History toHistory(HistoryDTO historyDTO, Wizard fromWizard, Wizard toWizard, Artifact artifact) {
        History history = new History(fromWizard, toWizard, artifact, historyDTO.getAction(), historyDTO.getTimestamp());
        return history;
    }
}
