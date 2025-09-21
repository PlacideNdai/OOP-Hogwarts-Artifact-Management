module javafxapp {
    requires javafx.controls;
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    
    exports com.example.hogwarts;
    exports com.example.hogwarts.model;
    exports com.example.hogwarts.view;
    exports com.example.hogwarts.controller;
    exports com.example.hogwarts.dto;
}
