package ru.vasileva.benchbackend;


import ru.vasileva.benchbackend.controller.Controller;
import ru.vasileva.benchbackend.model.MainModel;
import ru.vasileva.benchbackend.model.Model;
import ru.vasileva.benchbackend.view.ConsoleView;

public class Main {

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
//https://www.google.com/