package ru.vasileva.benchbackend.view;

import ru.vasileva.benchbackend.controller.Controller;
import ru.vasileva.benchbackend.model.ModelData;

public class ResultView implements View{

    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {

    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void eventOnStart () {
        controller.onStartShow();
    }

    public void eventOnFinish () {
        controller.onFinishShow();
    }

    public void eventResult() {
        controller.onResult();
    }

}
