package ru.vasileva.benchbackend.controller;

import ru.vasileva.benchbackend.model.Model;
import ru.vasileva.benchbackend.view.View;

import java.io.IOException;

/**
 * Class, that binds user interface and model of program
 */
public class Controller {
    private Model model;
    private View view;

    /**
     * Setter for model
     *
     * @param model
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * Setter for view
     *
     * @param view
     */
    public void setView(View view) {
        this.view = view;
    }

    /**
     * Sends to the model {@link Model} {@param url} received from the user
     * Sends to the view data form {@link ru.vasileva.benchbackend.model.ModelData}
     *
     * @param url
     *        URL-address for parsing
     *
     * @throws IOException
     *        if url has no protocol {@link java.net.MalformedURLException;}
     *        or HTTP Status is not OK {@link org.jsoup.HttpStatusException}
     *        or another I/O error occurs
     */
    public void requestAndResult(String url) throws IOException {
        model.processRequest(url);
        view.showResult(model.getModelData());
    }

    /**
     * Terminates the currently running program with normal termination.
     *
     * @throws SecurityException
     *        if a security manager exists and its <code>checkExit</code>
     *        method doesn't allow exit with the specified status.
     */
    public void exit() {
        System.exit(0);
    }
}
