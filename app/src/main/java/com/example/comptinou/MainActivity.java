package com.example.comptinou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button createStoryButton;
    private Button myStoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialiser les boutons
        createStoryButton = findViewById(R.id.createStoryButton);
        myStoriesButton = findViewById(R.id.myStoriesButton);

        // Ajouter les écouteurs d'événements
        createStoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Naviguer vers l'activité de création d'histoire
                Intent intent = new Intent(MainActivity.this, CreateStoryActivity.class);
                startActivity(intent);
            }
        });

        myStoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Naviguer vers l'activité Mes Histoires
                Intent intent = new Intent(MainActivity.this, MyStoriesActivity.class);
                startActivity(intent);
            }
        });
    }
}