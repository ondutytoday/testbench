package ru.vasileva.benchbackend.controller;

import ru.vasileva.benchbackend.model.Model;
import ru.vasileva.benchbackend.view.View;

import java.io.IOException;

public class Controller {
    private Model model;
    private View view;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void onResult(String url) throws IOException{
        model.processRequest(url);
        view.showResult(model.getModelData());
    }

    public void exit() {
        System.exit(0);
    }
}
