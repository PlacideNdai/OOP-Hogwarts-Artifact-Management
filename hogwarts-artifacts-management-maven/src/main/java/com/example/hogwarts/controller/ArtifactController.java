package com.example.hogwarts.controller;

import java.util.Collection;
import java.util.NoSuchElementException;

import com.example.hogwarts.data.DataStore;
import com.example.hogwarts.model.Artifact;
import com.example.hogwarts.model.History;

public class ArtifactController {
    private final DataStore store = DataStore.getInstance();

    public Collection<Artifact> findAllArtifacts() {
        return this.store.findAllArtifacts();
    }

    public Artifact addArtifact(String name, String description, Integer quality) {
        Artifact artifact = new Artifact(name, description, quality);
        return this.store.addArtifact(artifact);
    }

    public void updateArtifact(int id, String newName, String newDesc, int quality) {
        Artifact artifact = this.store.findArtifactById(id);
        if(artifact == null) {
            throw new NoSuchElementException("Artifact with ID " + id + " not found.");
        }
        artifact.setName(newName);
        artifact.setDescription(newDesc);
        artifact.setQuality(quality);
    }

    public void deleteArtifact(int id) {
        this.store.deleteArtifactById(id);
    }

    // ************************************************************************************************
    // adding the serach feature for artifacts below
    // ************************************************************************************************
    public Collection<Artifact> getSearchResults(String query){
        return this.store.findAllArtifactsByArtifactName(query);
    }

    // ************************************************************************************************
    // adding the history feature below
    // ************************************************************************************************
    public Collection<History>getArtifactHistory(Artifact artifact){
        return this.store.getArtifactHistory(artifact);
    }
}
