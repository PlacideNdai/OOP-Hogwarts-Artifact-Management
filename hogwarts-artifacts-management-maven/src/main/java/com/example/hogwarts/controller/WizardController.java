package com.example.hogwarts.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.hogwarts.data.DataStore;
import com.example.hogwarts.model.Artifact;
import com.example.hogwarts.model.Wizard;

public class WizardController {
    private final DataStore store = DataStore.getInstance();

    public Collection<Wizard> findAllWizards() {
        return this.store.findAllWizards();
    }

    public Wizard addWizard(String name) {
        Wizard wizard = new Wizard(name);
        return this.store.addWizard(wizard);
    }

    public void updateWizard(int id, String newName) {
        Wizard wizard = this.store.findWizardById(id);
        if(wizard == null) {
            throw new IllegalArgumentException("Wizard with ID " + id + " not found.");
        }
        wizard.setName(newName);
    }

    public void deleteWizard(int id) {
        this.store.deleteWizardById(id);
    }

    public boolean assignArtifactToWizard(Wizard wizard, Artifact artifact) {
        return this.store.assignArtifactToWizard(artifact.getId(), wizard.getId());
    }

    public List<Artifact> getUnassignedArtifacts() {
        return this.store.findAllArtifacts().stream()
                .filter(a -> a.getOwner() == null)
                .collect(Collectors.toList());
    }

    // ************************************************************************************************
    // unassigning the artifact below
    // ************************************************************************************************
    public void unassignWizardArtifact(Artifact artifact){
        if(artifact == null){
            return;
        }

        if(artifact.getOwner() == null){
            throw new IllegalStateException("Artifact with ID " + artifact.getId() + " is not assigned to any Wizard.");
        }

        this.store.unassignArtifact(artifact.getId(), artifact.getOwner().getId());
    }
}
