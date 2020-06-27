package ru.vasileva.benchbackend.model;

import java.util.Map;

public class ModelData {

    private Map<String, Long> mapOfWords;

    public Map<String, Long> getMapOfWords() {
        return mapOfWords;
    }

    public void setMapOfWords(Map<String, Long> mapOfWords) {
        this.mapOfWords = mapOfWords;
    }
}
