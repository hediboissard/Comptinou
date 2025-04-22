package com.example.comptinou;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateStoryActivity extends AppCompatActivity {

    private EditText heroNameInput;
    private Button generateButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_story);

        heroNameInput = findViewById(R.id.heroNameInput);
        generateButton = findViewById(R.id.generateButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logique pour générer l'histoire
                // Puis naviguer vers l'écran de détail de l'histoire

                Intent intent = new Intent(CreateStoryActivity.this, StoryDetailActivity.class);
                // Ajouter des données à transmettre à l'activité suivante si nécessaire
                intent.putExtra("HERO_NAME", heroNameInput.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Retour à l'activité précédente
        finish();
    }
}