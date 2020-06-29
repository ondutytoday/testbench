package ru.vasileva.benchbackend.model;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MainModel implements Model {

    private ModelData modelData = new ModelData();
    private boolean isUnverified = false;

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
    @Override
    public void processRequest(String url) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HttpURLConnection connection = null;
        try {
            if (isUnverified) {
                sslVerification();
            }
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new HttpStatusException("Server returned HTTP "
                        + connection.getResponseCode() + " "
                        + connection.getResponseMessage(),
                        connection.getResponseCode(), url);
            }
            String contentCharset = connection.getContentEncoding();
            Path file = Paths.get("DownloadedHTML.html");
            modelData.setFilePath(file);
            copyToFile(connection, file);
            String text = parsePage(contentCharset, file);
            modelData.setMapOfWords(makeWordsMap(text));
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

    private void sslVerification() throws NoSuchAlgorithmException, KeyManagementException {

        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                public void checkServerTrusted(X509Certificate[] certs, String authType) { }
            }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) { return true; }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

    }

    /**
     * Switches SSLVerification
     *
     * @param isUnverified
     *        if true - ssl verification switched off
     */
    @Override
    public void setSSLVerification(boolean isUnverified) {
        this.isUnverified = isUnverified;
    }

    /**
     * Copies html page to file
     *
     * @param connection
     *        connection with URL-address
     *
     * @param filePath
     *        file for saving html
     *
     * @throws IOException
     *         if I/O error occurs
     */
    private void copyToFile(HttpURLConnection connection, Path filePath) throws IOException {
        Files.copy(connection.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Parsing of html-page
     *
     * @param contentCharset
     *        content encoding of html-page
     *
     * @param pathToDownload
     *        name of html file with content
     *
     * @return string with text from body of our html page
     *
     * @throws IOException
     *         if I/O error occurs
     */
    private String parsePage(String contentCharset, Path pathToDownload) throws IOException {
        Document doc = Jsoup.parse(pathToDownload.toFile(), contentCharset);
        Element body = doc.body();
        String text = body.text();
        return text;
    }

    /**
     * Splits text to words using delimeters from test-task,
     * adds words to map in its UPPERCASE as it was shown in test-task and increment its count,
     *
     * @param text
     *        text content of body-part of html page
     *
     * @return map of words and count in the natural ordering of words
     */
    private static Map<String, Long> makeWordsMap(String text) {
        StringTokenizer st = new StringTokenizer(text, " ,.!?\";:[]()\n\r\t");
        Map<String, Long> map = new TreeMap<>();
        while (st.hasMoreTokens()) {
            String word = st.nextToken().toUpperCase();
            map.merge(word, 1l, (oldVal, newVal) -> oldVal + 1);
        }
        return map;
    }

    /**
     * Getter for modelData
     *
     * @return actual {@link ModelData} instance;
     */
    public ModelData getModelData() {
        return modelData;
    }

}
