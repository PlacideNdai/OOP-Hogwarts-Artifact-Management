package com.example.hogwarts.data;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.example.hogwarts.model.Artifact;
import com.example.hogwarts.model.History;
import com.example.hogwarts.model.Wizard;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonPersistance {
    private final DataStore store = DataStore.getInstance();
    private final ObjectMapper mapper = new ObjectMapper();
    private final String storeLocation = "data/";

    public JsonPersistance() {
        mapper.registerModule(new JavaTimeModule());
    }

    public void loadDataOnStartUp(String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return;
            } else {
                // ************************************************************************************************
                // loading data from json file.
                // ************************************************************************************************

                if (file.getName().toLowerCase().contains("wizard")) {
                    String jsonConvert = jsonConvert(file);
                    Map<Integer, Wizard> loadingWizards = mapper.readValue(jsonConvert,
                            new TypeReference<Map<Integer, Wizard>>() {
                            });

                    for (Map.Entry<Integer, Wizard> entry : loadingWizards.entrySet()) {
                        store.addWizard(entry.getValue());
                    }

                    // ************************************************************************************************
                    // loading data from json file.
                    // ************************************************************************************************

                } else if (file.getName().toLowerCase().contains("artifact")) {
                    String jsonConvert = jsonConvert(file);
                    Map<Integer, Artifact> loadArtifacts = mapper.readValue(jsonConvert,
                            new TypeReference<Map<Integer, Artifact>>() {
                            });

                    for (Map.Entry<Integer, Artifact> entry : loadArtifacts.entrySet()) {
                        store.addArtifact(entry.getValue());
                    }

                    // ************************************************************************************************
                    // loading data from json file.
                    // ************************************************************************************************

                } else if (file.getName().toLowerCase().contains("transfer")) {
                    String jsonConvert = jsonConvert(file);
                    Map<Artifact, List<History>> loadArtifactHistory = mapper.readValue(jsonConvert,
                            new TypeReference<Map<Artifact, List<History>>>() {
                            });

                    for (Map.Entry<Artifact, List<History>> entry : loadArtifactHistory.entrySet()) {

                        Artifact artifact = entry.getKey();
                        List<History> artifactHistory = entry.getValue();

                        for (History h : artifactHistory) {
                            store.addToHistory(h.getFromWizard(), h.getToWizard(), artifact, h.getAction());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String jsonConvert(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            return null;
        }
    }

    public void writeIntoJsonOnExist(Object object, String fileName) {
        try {
            File fileToWriteInto = new File( storeLocation + fileName);

            if (fileToWriteInto.getParentFile() != null) {
                fileToWriteInto.getParentFile().mkdirs();
            }

            // write the data into the file.
            mapper.writerWithDefaultPrettyPrinter().writeValue(fileToWriteInto, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}