package ru.vasileva.benchbackend;


import ru.vasileva.benchbackend.controller.Controller;
import ru.vasileva.benchbackend.model.MainModel;
import ru.vasileva.benchbackend.model.Model;
import ru.vasileva.benchbackend.view.ConsoleView;

/**
 * Class, that provides external entry points for the program.
 * @author Elena Vasileva
 * @version 1.0
 */
public class Main {

    /**
     * Entry point
     *
     * @param args
     *        have no args
     */
    public static void main(String[] args) {
        Model model = new MainModel();
        ConsoleView view = new ConsoleView();
        Controller controller = new Controller();

        view.setController(controller);
        controller.setModel(model);
        controller.setView(view);

        view.init();
    }
}
