package ru.vasileva.benchbackend.view;

import ru.vasileva.benchbackend.controller.Controller;
import ru.vasileva.benchbackend.model.ModelData;

public interface View {
    void refresh (ModelData modelData);
    void setController (Controller controller);
}
