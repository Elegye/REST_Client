package com.eseo.client.service;

import com.eseo.client.dto.DtoInterface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RequestService {

    public static String get(String URL) throws IOException {

        URL url = new URL(URL);

        String rawResponse = "";

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new RuntimeException("La requête a échouée avec un code : " + responseCode);
        }
        else {

            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                rawResponse += scanner.nextLine();
            }
            scanner.close();
        }

        return rawResponse;
    }

    public static int create(Object dto) throws IOException {
        URL url = new URL(URLBuilder.CREATE_VILLE);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        Gson gson = new Gson();
        String requestBody = gson.toJson(dto);

        OutputStream outStream = conn.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outStream, "UTF-8");
        outStreamWriter.write(requestBody);
        outStreamWriter.flush();
        outStreamWriter.close();
        outStream.close();
        conn.connect();

        return conn.getResponseCode();
    }

}
