package com.example.comptinou;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StoryDetailActivity extends AppCompatActivity {

    private TextView storyDetailTitle;
    private TextView storyDetailContent;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        storyDetailTitle = findViewById(R.id.storyDetailTitle);
        storyDetailContent = findViewById(R.id.storyDetailContent);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Récupérer les données passées à cette activité
        if (getIntent().hasExtra("STORY_ID")) {
            String storyId = getIntent().getStringExtra("STORY_ID");
            // Charger l'histoire avec cet ID
            loadStoryDetails(storyId);
        }

        if (getIntent().hasExtra("HERO_NAME")) {
            String heroName = getIntent().getStringExtra("HERO_NAME");
            // Utiliser le nom du héros pour personnaliser l'histoire
        }
    }

    private void loadStoryDetails(String storyId) {
        // Implémenter la logique pour charger les détails de l'histoire
        // à partir de votre source de données
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Gérer le bouton retour dans la barre d'action
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}