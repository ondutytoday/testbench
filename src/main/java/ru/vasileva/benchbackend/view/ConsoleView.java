package ru.vasileva.benchbackend.view;

import org.jsoup.HttpStatusException;
import ru.vasileva.benchbackend.controller.Controller;
import ru.vasileva.benchbackend.model.ModelData;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Class of view, that shows information on console
 */
public class ConsoleView implements View {

    private Controller controller;

    /**
     * Asks for entering URL-address from user,
     * or "exit" for closing the program
     */
    public void init() {
        while (true) {
            try {
                ConsoleHelper.writeMessage("Enter the site address or \"exit\" to close the application:");
                String string = ConsoleHelper.readString();
                if ("exit".equalsIgnoreCase(string)) {
                    eventOnExit();
                } else {
                    controller.requestAndResult(string);
                }
            } catch (HttpStatusException h) {
                ConsoleHelper.writeMessage(h.getMessage());
            }
            catch (MalformedURLException m) {
                ConsoleHelper.writeMessage("You have entered the wrong address. Try again.");
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Something was wrong ... try again.");
                ConsoleHelper.writeMessage(e.getMessage());
            }
        }
    }

    /**
     * Shows the result of request like it was shown in test-task
     * Shows absolute path of file
     *
     * @param modelData
     *        data which contains information for showing
     */
    @Override
    public void showResult(ModelData modelData) {
        Map<String, Long> map = modelData.getMapOfWords();
        map.forEach((k, v) -> {
            ConsoleHelper.writeMessage(k + " - " + v);
        });
        ConsoleHelper.writeMessage("The file is located at: " + modelData.getFilePath().toAbsolutePath().toString());
    }

    /**
     * Terminates the currently running program with normal termination.
     *
     * @throws SecurityException
     *        if a security manager exists and its <code>checkExit</code>
     *        method doesn't allow exit with the specified status.
     */
    public void eventOnExit() {
        ConsoleHelper.writeMessage("Завершение...");
        controller.exit();
    }

    /**
     * Setter for {@link Controller} instance
     *
     * @param controller
     *        controller that binds user interface and model of program
     */
    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
