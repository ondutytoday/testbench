package ru.vasileva.benchbackend.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Static class, that helps to write and to take string messages to and from console
 */
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Writes message
     *
     * @param message
     */
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads string from console
     *
     * @return string message
     *
     * @throws IOException
     *         if I/O error occurs
     */
    public static String readString() throws IOException {
        String text = bis.readLine();
        return text;
    }
}
