package ru.vasileva.benchbackend.model;

import java.nio.file.Path;
import java.util.Map;

/**
 * Class, that contains data of words and its count and path to file
 */
public class ModelData {

    private Map<String, Long> mapOfWords;
    private Path filePath;

    /**
     * Getter for file
     *
     * @return relative path of file (filename) for saving the html page
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * Setter for file
     *
     * @param filePath
     *        name of file for saving the html page
     */
    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Getter for map of words
     *
     * @return map of words and count of words
     */
    public Map<String, Long> getMapOfWords() {
        return mapOfWords;
    }

    /**
     * Setter for map of words
     *
     * @param mapOfWords
     *        map of words and count of words
     */
    public void setMapOfWords(Map<String, Long> mapOfWords) {
        this.mapOfWords = mapOfWords;
    }
}
