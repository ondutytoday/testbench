package ru.vasileva.benchbackend.model;

import java.io.IOException;

public interface Model {
    ModelData getModelData();

    void processRequest(String url) throws IOException;
}
