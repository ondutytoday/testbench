package ru.vasileva.benchbackend.controller;

import ru.vasileva.benchbackend.model.Model;
import ru.vasileva.benchbackend.view.View;

public class Controller {
    private Model model;
    private View view;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void onStartShow () {

    }


    public void onFinishShow() {

    }

    public void onResult(String url) {
        model.getRequest(url);
        view.showResult(model.getModelData());
    }
}
