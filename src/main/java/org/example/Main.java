package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try {

            URL url = new URL("https://quizizz.com/quiz/61e5162461e3e1001de662c4");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            String content = null;
            while ((output = br.readLine()) != null) {
                content = mapper.readTree(output).toString();
//                System.out.println(output);
            }
            conn.disconnect();

            System.out.println(content);
            Content data = mapper.readValue(content, Content.class);
            System.out.println(data);
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
    }


}