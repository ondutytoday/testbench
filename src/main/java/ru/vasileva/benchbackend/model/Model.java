package ru.vasileva.benchbackend.model;

public interface Model {
    ModelData getModelData();

    void getRequest(String url);
}
