package ru.vasileva.benchbackend.model;

import java.nio.file.Path;
import java.util.Map;

public class ModelData {

    private Map<String, Long> mapOfWords;
    private Path filePath;

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public Map<String, Long> getMapOfWords() {
        return mapOfWords;
    }

    public void setMapOfWords(Map<String, Long> mapOfWords) {
        this.mapOfWords = mapOfWords;
    }
}
