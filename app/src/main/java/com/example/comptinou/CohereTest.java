package com.example.comptinou;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class CohereTest {

    private static final String API_URL = "https://api.cohere.com/v2/chat";
    private static final String API_KEY = "FV2LkRswQE0UPoAMXUQefCsoSp5PPe0YRPCpqOlF";

    public static void main(String[] args) {
        try {
            makePostRequestToCohere();
        } catch (Exception e) {
            Log.e("CohereApiClient", "Erreur lors de la requête API Cohere", e);
        }
    }

    public static void makePostRequestToCohere() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setDoOutput(true);

        JSONObject json = new JSONObject();
        json.put("model", "command-a-03-2025");
        json.put("prompt", "Write a one-sentence bedtime story about a unicorn.");
        json.put("max_tokens", 50);

        try (OutputStream os = urlConnection.getOutputStream()) {
            byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int responseCode = urlConnection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Body: " + response);
    }
}