package com.example.hogwarts.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.hogwarts.dto.ArtifactDTO;
import com.example.hogwarts.dto.FromDtoConverter;
import com.example.hogwarts.dto.HistoryDTO;
import com.example.hogwarts.dto.WizardDTO;
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
            String fullPath = storeLocation + fileName;
            File file = new File(fullPath);
            if (!file.exists()) {
                return;
            } else {
                // ************************************************************************************************
                // loading data from DTOs.
                // ************************************************************************************************

                if (file.getName().toLowerCase().contains("wizard")) {
                    // loading wizards from WizardDTO.
                    List<WizardDTO> wizardDTOs = mapper.readValue(file, new TypeReference<List<WizardDTO>>() {
                    });

                    Map<Integer, Wizard> wizards = wizardDTOs.stream().map(FromDtoConverter::toWizard)
                            .collect(Collectors.toMap(Wizard::getId, w -> w));
                    wizards.values().forEach(store::addWizard);

                } else if (file.getName().toLowerCase().contains("artifact")) {
                    // loading artifacts from ArtifactDTO.
                    List<ArtifactDTO> artifactDTOs = mapper.readValue(file, new TypeReference<List<ArtifactDTO>>() {
                    });

                    Map<Integer, Artifact> artifacts = artifactDTOs.stream().map(FromDtoConverter::toArtifact)
                            .collect(Collectors.toMap(Artifact::getId, a -> a));
                    artifacts.values().forEach(store::addArtifact);

                } else if (file.getName().toLowerCase().contains("transfer")) {
                    // loading history from HistoryDTO.

                    Map<String, HistoryDTO> historyDTOMap = mapper.readValue(
                            file, new TypeReference<Map<String, HistoryDTO>>() {
                            });

                    List<HistoryDTO> historyDTOs = new ArrayList<>(historyDTOMap.values());

                    List<History> histories = historyDTOs.stream().map(historyDTO -> {
                        Wizard fromWizard = store.findWizardByName(historyDTO.getFromWizard());
                        Wizard toWizard = store.findWizardByName(historyDTO.getToWizard());
                        Artifact artifact = store.findArtifactById(historyDTO.getArtifact());

                        return FromDtoConverter.toHistory(historyDTO, fromWizard, toWizard, artifact);
                    }).collect(Collectors.toList());

                    histories.forEach(his -> store.addToHistory(his.getFromWizard(), his.getToWizard(),
                            his.getArtifact(), his.getAction()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void writeIntoJsonOnExist(Object object, String fileName) {
        try {
            File fileToWriteInto = new File(storeLocation + fileName);

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