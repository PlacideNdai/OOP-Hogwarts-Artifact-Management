package com.example.hogwarts.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.hogwarts.data.DataStore;
import com.example.hogwarts.data.JsonPersistance;
import com.example.hogwarts.dto.ArtifactDTO;
import com.example.hogwarts.dto.ToDTOConverter;
import com.example.hogwarts.dto.HistoryDTO;
import com.example.hogwarts.dto.WizardDTO;

public class JsonPersistanceController {
    private final JsonPersistance jsonPersistance = new JsonPersistance();
    private final DataStore store = DataStore.getInstance();

    public JsonPersistanceController() {

    }

    public void saveOnExit() {
        // converting to DTO.
        List<WizardDTO> wizardDTOs = store.findAllWizards().stream().map(w -> {
            return ToDTOConverter.toWizardDto(w);
        }).collect(Collectors.toList());

        List<ArtifactDTO> artifactDTOs = store.findAllArtifacts().stream().map(a -> {
            return ToDTOConverter.toArtifactDTO(a);
        }).collect(Collectors.toList());

        Map<Integer, List<HistoryDTO>> historyDTOs = store.findAllHistory().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getId(),
                        entry -> entry.getValue().stream().map(ToDTOConverter::toHistoryDTO)
                                .collect(Collectors.toList())));

        jsonPersistance.writeIntoJsonOnExist(artifactDTOs, "artifacts.json");
        jsonPersistance.writeIntoJsonOnExist(wizardDTOs, "wizards.json");
        jsonPersistance.writeIntoJsonOnExist(historyDTOs, "transfers.json");
    }

    public void loadDataOnStartUp() {
        // jsonPersistance.loadDataOnStartUp("artifacts.json");
        // jsonPersistance.loadDataOnStartUp("wizards.json");
        // jsonPersistance.loadDataOnStartUp("transfers.json");
    }
}
