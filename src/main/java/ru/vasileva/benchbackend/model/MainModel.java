package ru.vasileva.benchbackend.model;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class MainModel implements Model {

    private ModelData modelData = new ModelData();

    private static Map<String, Long> makeWordsMap(String text) {
        StringTokenizer st = new StringTokenizer(text, " ,.!?\";:[]()\n\r\t");
        Map<String, Long> map = new TreeMap<>();
        while (st.hasMoreTokens()) {
            String word = st.nextToken().toUpperCase();
            map.merge(word, 1l, (oldVal, newVal) -> oldVal + 1);
        }
        return map;
    }

    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void processRequest(String url) throws IOException {
        HttpURLConnection connection = null;
        try {
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

    private void copyToFile(HttpURLConnection connection, Path filePath) throws IOException {
        Files.copy(connection.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    private String parsePage(String contentCharset, Path pathToDownload) throws IOException {
        Document doc = Jsoup.parse(pathToDownload.toFile(), contentCharset);
        Element body = doc.body();
        String text = body.text();
        return text;
    }

}
