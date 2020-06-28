package ru.vasileva.benchbackend.view;

import org.jsoup.HttpStatusException;
import ru.vasileva.benchbackend.controller.Controller;
import ru.vasileva.benchbackend.model.ModelData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

public class ConsoleView implements View {

    private Controller controller;

    @Override
    public void showResult(ModelData modelData) {
        Map<String, Long> map = modelData.getMapOfWords();
        map.forEach((k, v) -> {
            ConsoleHelper.writeMessage(k + " - " + v);
        });
        ConsoleHelper.writeMessage("The file is located at: " + modelData.getFilePath().toAbsolutePath().toString());
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        while (true) {
            try {
                ConsoleHelper.writeMessage("Enter the site address or \"exit\" to close the application:");
                String string = ConsoleHelper.readString();
                if ("exit".equalsIgnoreCase(string)) {
                    eventOnExit();
                } else {
                    controller.onResult(string);
                }
            } catch (HttpStatusException h) {
                ConsoleHelper.writeMessage(h.getMessage());
            }
            catch (MalformedURLException m) {
                ConsoleHelper.writeMessage("You have entered the wrong address. Try again.");
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Something was wrong ... try again.");
            }
        }
    }

    public void eventOnExit() {
        ConsoleHelper.writeMessage("Завершение...");
        controller.exit();
    }

}
