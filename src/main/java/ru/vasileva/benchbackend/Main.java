package ru.vasileva.benchbackend;


import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
/*            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            Path path = Paths.get("downloaded.html");
            try {
                URL url = new URL("http://google.com");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    System.out.println( "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage());
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();

                output = new FileOutputStream(path.toFile());

                byte data[] = new byte[4096];
                int count;
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }*/
            HttpURLConnection con = (HttpURLConnection) new URL("https://4pda.ru/forum/index.php?showtopic=684857").openConnection();
            con.setRequestMethod("GET");
            String charset = con.getContentEncoding();
            System.out.println(charset);
            Path path = Paths.get("downloaded.html"); //charset - ???
            Files.copy(con.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING );
            Document doc = Jsoup.parse(path.toFile(),charset);
            Element body = doc.body();
            String s = body.text();
            Map<String, Long> map = getStringLongMap(s);
            System.out.println(map.toString());
            System.out.println(s);
        } catch (HttpStatusException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Long> getStringLongMap(String s) {
        StringTokenizer st = new StringTokenizer(s, " ,.!?\";:[]()\n\r\t");
        Map<String, Long> map = new TreeMap<>();
        while (st.hasMoreTokens()) {
            String ss = st.nextToken().toUpperCase();
            map.merge(ss,  1l, (oldVal, newVal) -> oldVal + 1);
            //System.out.println(ss);
        }
        return map;
    }

}
        /*Elements elements = body.getElementsByTag("div");
        for (Element el : elements) {
            String s = el.text();
            System.out.println(s);
        }*/