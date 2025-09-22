package com.example.hogwarts.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.hogwarts.model.Artifact;
import com.example.hogwarts.model.History;
import com.example.hogwarts.model.Role;
import com.example.hogwarts.model.User;
import com.example.hogwarts.model.Wizard;

/**
 * TODO: Make this a thread-safe singleton
 * TODO: Use atomic integers for ID generation to avoid race conditions
 */
public class DataStore {
    private static DataStore instance; // Singleton instance

    private final List<User> users = new ArrayList<>();
    private final Map<Integer, Wizard> wizards = new HashMap<>();
    private final Map<Integer, Artifact> artifacts = new HashMap<>();
    private final Map<Artifact, List<History>> artifactHistories = new HashMap<>();

    private int wizardIdCounter = 1; // Wizard ID generator
    private int artifactIdCounter = 1; // Artifact ID generator

    private User currentUser; // Currently authenticated user

    private DataStore() {
        // Hardcoded users
        this.users.add(new User("admin", "123", Role.ADMIN));
        this.users.add(new User("user", "123", Role.USER));
        this.users.add(new User("user2", "123", Role.USER));
        this.users.add(new User("user3", "123", Role.USER));

        // Sample data
        // Wizard w1 = new Wizard("Harry Potter");
        // Wizard w2 = new Wizard("Hermione Granger");

        // this.addWizard(w1);
        // this.addWizard(w2);

        // Artifact a1 = new Artifact("Invisibility Cloak", "A magical cloak that makes the wearer invisible.", 100);
        // Artifact a2 = new Artifact("Time-Turner", "A device used for time travel.", 100);
        
        // this.addArtifact(a1);
        // this.addArtifact(a2);

        // this.assignArtifactToWizard(a1.getId(), w1.getId());
        // this.assignArtifactToWizard(a2.getId(), w2.getId());
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    // User authentication
    public User authenticate(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    // Wizards
    public Wizard addWizard(Wizard wizard) {
        wizard.setId(wizardIdCounter++);
        this.wizards.put(wizard.getId(), wizard);
        return wizard;
    }

    public void deleteWizardById(int id) {
        Wizard wizard = this.wizards.remove(id);
        if (wizard != null) {
            wizard.removeAllArtifacts();
        }
    }

    public Collection<Wizard> findAllWizards() {
        return this.wizards.values();
    }

    public Wizard findWizardById(int id) {
        return this.wizards.get(id);
    }

    // getting the wizard by name.
    public Wizard findWizardByName(String name){
        return this.wizards.values().stream().filter(w -> w.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    // Artifacts
    public Artifact addArtifact(Artifact artifact) {
        artifact.setId(artifactIdCounter++);
        this.artifacts.put(artifact.getId(), artifact);
        return artifact;
    }

    public void deleteArtifactById(int id) {
        Artifact artifact = this.artifacts.remove(id);
        if (artifact != null && artifact.getOwner() != null) {
            artifact.getOwner().removeArtifact(artifact);
        }
    }

    public Collection<Artifact> findAllArtifacts() {
        return this.artifacts.values();
    }

    public Artifact findArtifactById(int id) {
        return this.artifacts.get(id);
    }

    public boolean assignArtifactToWizard(int artifactId, int wizardId) {
        Artifact artifact = this.artifacts.get(artifactId);
        Wizard wizard = this.wizards.get(wizardId);
        if (artifact == null || wizard == null)
            return false;

        // quality check.
        if (artifact.getQuality() < artifact.MINMAL_TRADE_QUALITY) {
            return false;
        }

        artifact.setMinusQuality(artifact);
        // adding to the history.
        addToHistory(artifact.getOwner(), wizard, artifact, History.ASSIGN);
        wizard.addArtifact(artifact);
        return true;
    }

    // ************************************************************************************************
    // adding the serach feature for artifacts below
    // ************************************************************************************************
    public Collection<Artifact> findAllArtifactsByArtifactName(String artifactName) {
        String searchName = artifactName.toLowerCase();
        return this.artifacts.values().stream().filter(a -> a.getName().toLowerCase().contains(searchName)).toList();
    }

    // ************************************************************************************************
    // adding the serach feature for artifacts above
    // ************************************************************************************************

    // users
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    // ************************************************************************************************
    // updating the DB after unassign artifact below
    // ************************************************************************************************

    public boolean unassignArtifact(int artifactId, int wizardId) {
        Artifact artifact = this.artifacts.get(artifactId);
        Wizard wizard = this.wizards.get(wizardId);

        if (artifact == null || wizard == null)
            return false;

        // record in the history.
        addToHistory(wizard, null, artifact, History.UNASSIGN);
        wizard.removeArtifact(artifact);
        return true;
    }

    // ************************************************************************************************
    // History feature below
    // ************************************************************************************************

    // returning the artifact history.
    public List<History> getArtifactHistory(Artifact artifact) {
        if (artifact == null)
            return List.of();

        return artifactHistories.getOrDefault(artifact, List.of());
    }

    // ************************************************************************************************
    // getting all history.
    // ************************************************************************************************

    public Map<Artifact, List<History>> findAllHistory() {
        return artifactHistories;
    }

    // adding to the history method.
    public void addToHistory(Wizard fromWizard, Wizard toWizard, Artifact artifact, String action) {
        artifactHistories.computeIfAbsent(artifact, k -> new ArrayList<>())
                .add(new History(fromWizard, toWizard, artifact, action));
    }
}
