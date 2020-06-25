package ru.vasileva.benchbackend.model;

import java.util.Map;

public class ModelData {

    private Map<String, Long> wordsAndCount;

    public Map<String, Long> getWordsAndCount() {
        return wordsAndCount;
    }

    public void setWordsAndCount(Map<String, Long> wordsAndCount) {
        this.wordsAndCount = wordsAndCount;
    }
}
