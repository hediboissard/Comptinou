package com.example.comptinou;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CreateStoryActivity extends AppCompatActivity {

    private EditText heroNameInput;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);

        heroNameInput = findViewById(R.id.heroNameInput);
        Button generateButton = findViewById(R.id.generateButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String heroName = heroNameInput.getText().toString();
                generateStory(heroName);
            }
        });
    }

    private void generateStory(String heroName) {
        executorService.execute(() -> {
            try {
                HttpURLConnection urlConnection = getHttpURLConnection(heroName);
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                        StringBuilder response = new StringBuilder();
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        runOnUiThread(() -> {
                            Intent intent = new Intent(CreateStoryActivity.this, StoryDetailActivity.class);
                            intent.putExtra("STORY_TEXT", response.toString());
                            startActivity(intent);
                        });
                    }
                } else {
                    Log.e("CreateStoryActivity", "Erreur API : " + responseCode);
                }
            } catch (Exception e) {
                Log.e("CreateStoryActivity", "Erreur lors de la génération de l'histoire", e);
            }
        });
    }

    @NonNull
    private static HttpURLConnection getHttpURLConnection(String heroName) throws IOException, JSONException {
        URL url = new URL("https://api.cohere.com/v2/chat");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Authorization", "Bearer FV2LkRswQE0UPoAMXUQefCsoSp5PPe0YRPCpqOlF");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setDoOutput(true);

        JSONObject json = new JSONObject();
        json.put("model", "command-a-03-2025");
        json.put("prompt", "Genere une histoire avec comme nom du héro " + heroName);
        json.put("max_tokens", 50);

        try (OutputStream os = urlConnection.getOutputStream()) {
            byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        return urlConnection;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}