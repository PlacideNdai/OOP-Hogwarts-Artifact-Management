package com.example.hogwarts.controller;

import java.util.Collection;
import java.util.NoSuchElementException;

import com.example.hogwarts.data.DataStore;
import com.example.hogwarts.model.Artifact;

public class ArtifactController {
    private final DataStore store = DataStore.getInstance();

    public Collection<Artifact> findAllArtifacts() {
        return this.store.findAllArtifacts();
    }

    public Artifact addArtifact(String name, String description) {
        Artifact artifact = new Artifact(name, description);
        return this.store.addArtifact(artifact);
    }

    public void updateArtifact(int id, String newName, String newDesc) {
        Artifact artifact = this.store.findArtifactById(id);
        if(artifact == null) {
            throw new NoSuchElementException("Artifact with ID " + id + " not found.");
        }
        artifact.setName(newName);
        artifact.setDescription(newDesc);
    }

    public void deleteArtifact(int id) {
        this.store.deleteArtifactById(id);
    }

    // // update this later.
    // public void unassignArtifact(int id){
    //     Artifact artifact = this.store.findArtifactById(id);

    //     if( artifact == null ) {
    //         throw new NoSuchElementException("Artifact with ID " + id + " not found.");
    //     }

    //     if( artifact.getOwner() == null ) {
    //         throw new IllegalStateException("Artifact with ID " + id + " is not assigned to any Wizard.");
    //     }

    //     // artifact.setOwner(null);
    // }

    // ************************************************************************************************
    // adding the serach feature for artifacts below
    // ************************************************************************************************
    public Collection<Artifact> getSearchResults(String query){
        return this.store.findAllArtifactsByArtifactName(query);
    }

    // ************************************************************************************************
    // adding the serach feature for artifacts above
    // ************************************************************************************************
}
