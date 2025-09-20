package com.example.hogwarts.controller;

import com.example.hogwarts.data.DataStore;
import com.example.hogwarts.data.JsonPersistance;


public class JsonPersistanceController {
    private final JsonPersistance jsonPersistance = new JsonPersistance();
    private final DataStore store = DataStore.getInstance();
    
    public JsonPersistanceController() {
        
    }

    public void saveOnExit() {
        jsonPersistance.writeIntoJsonOnExist(store.findAllArtifacts(), "artifacts.json");
        jsonPersistance.writeIntoJsonOnExist(store.findAllWizards(), "wizards.json");
        jsonPersistance.writeIntoJsonOnExist(store.findAllHistory(), "transfers.json");
    }

    public void loadDataOnStartUp(){
        jsonPersistance.loadDataOnStartUp("artifacts.json");
        jsonPersistance.loadDataOnStartUp("wizards.json");
        jsonPersistance.loadDataOnStartUp("transfers.json");
    }
}
