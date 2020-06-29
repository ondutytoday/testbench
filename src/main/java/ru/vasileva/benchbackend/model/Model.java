package ru.vasileva.benchbackend.model;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Interface, that contains logic of program
 */
public interface Model {
    ModelData getModelData();

    /**
     * Accepts and processes the URL-address,
     * sends words and file to {@link ModelData},
     * copies html page to file
     *
     * @param url
     *        URL-address entered by user

     * @throws IOException
     *        if URL-address has no protocol {@link java.net.MalformedURLException;}
     *        or HTTP Status is not OK {@link org.jsoup.HttpStatusException}
     *        or another I/O error occurs
     */
    void processRequest(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException;

    /**
     * Switchs SSLVerification
     *
     * @param isUnverified
     *        if true - ssl verification switched off
     */
    void setSSLVerification(boolean isUnverified);

}
