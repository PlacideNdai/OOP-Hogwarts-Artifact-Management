package com.example.hogwarts.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.hogwarts.model.Artifact;
import com.example.hogwarts.model.History;
import com.example.hogwarts.model.Wizard;

public class DTOConverter {
    // ************************************************************************************************
    // converting wizard to DTO.
    // ************************************************************************************************

    public static WizardDTO toWizardDto(Wizard wizard) {
        List<ArtifactDTO> artifactsOwned = wizard.getArtifacts().stream()
                .map(a -> new ArtifactDTO(a.getId(), a.getOwner().getId(), a.getName(), a.getDescription(),
                        a.getQuality()))
                .collect(Collectors.toList());

        return new WizardDTO(wizard.getId(), wizard.getName(), artifactsOwned);
    }

    // ************************************************************************************************
    // converting artifact to DTO.
    // ************************************************************************************************

    public static ArtifactDTO toArtifactDTO(Artifact artifact) {
        return new ArtifactDTO(artifact.getId(), (artifact.getOwner() != null ? artifact.getOwner().getId() : -1),
                artifact.getName(), artifact.getDescription(), artifact.getQuality());
    }

    // ************************************************************************************************
    // converting history to DTO.
    // ************************************************************************************************

    public static HistoryDTO toHistoryDTO(History history) {
        return new HistoryDTO(history.getAction(), history.getFromWizard().getName(), history.getToWizard().getName(),
                history.getArtifact().getId(), history.getTimestamp());
    }
}
