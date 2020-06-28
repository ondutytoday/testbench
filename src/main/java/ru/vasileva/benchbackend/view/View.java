package ru.vasileva.benchbackend.view;

import ru.vasileva.benchbackend.controller.Controller;
import ru.vasileva.benchbackend.model.ModelData;

/**
 * Interface of user view
 */
public interface View {

    /**
     * Shows the result of request
     *
     * @param modelData
     *        data which contains information for showing
     */
    void showResult(ModelData modelData);

    /**
     * Setter for {@link Controller} instance
     *
     * @param controller
     *        controller that binds user interface and model of program
     */
    void setController (Controller controller);
}
