package com.example.comptinou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MyStoriesActivity extends AppCompatActivity {

    private RecyclerView storiesRecyclerView;
    private TextView emptyStateText;
    private FloatingActionButton newStoryFab;
    private StoryAdapter adapter;
    private List<Story> storyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stories);

        // Initialisation des vues
        storiesRecyclerView = findViewById(R.id.storiesRecyclerView);
        emptyStateText = findViewById(R.id.emptyStateText);
        newStoryFab = findViewById(R.id.newStoryFab);

        // Configuration du RecyclerView
        storiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialisation des données
        storyList = loadStories();

        // Configuration de l'adaptateur
        adapter = new StoryAdapter(storyList, this);
        storiesRecyclerView.setAdapter(adapter);

        // Afficher le message d'état vide si nécessaire
        updateEmptyState();

        // Configuration du bouton d'ajout d'histoire
        newStoryFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyStoriesActivity.this, CreateStoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recharger les histoires à chaque fois que l'activité redevient visible
        storyList.clear();
        storyList.addAll(loadStories());
        adapter.notifyDataSetChanged();
        updateEmptyState();
    }

    private void updateEmptyState() {
        if (storyList.isEmpty()) {
            emptyStateText.setVisibility(View.VISIBLE);
            storiesRecyclerView.setVisibility(View.GONE);
        } else {
            emptyStateText.setVisibility(View.GONE);
            storiesRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    // Méthode pour charger les histoires (dans une application réelle,
    // ces données viendraient d'une base de données ou d'une API)
    private List<Story> loadStories() {
        List<Story> stories = new ArrayList<>();

        // Pour la démonstration, nous allons créer quelques histoires factices
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.FRENCH);
        String currentDate = sdf.format(new Date());

        stories.add(new Story("1", "L'aventure de Sophie",
                "Il était une fois une petite fille nommée Sophie qui adorait explorer le monde. Un jour, alors qu'elle se promenait dans la forêt près de chez elle, elle découvrit un petit sentier qu'elle n'avait jamais remarqué auparavant...",
                currentDate, "Sophie"));

        stories.add(new Story("2", "Le voyage de Lucas",
                "Lucas était un petit garçon très curieux. Il rêvait de voyager dans l'espace. Un soir, alors qu'il regardait les étoiles depuis sa fenêtre, une lumière étrange apparut dans le ciel...",
                currentDate, "Lucas"));

        return stories;
    }

    // Méthode appelée par l'adaptateur lorsqu'une histoire est cliquée
    public void onStoryClick(String storyId) {
        Intent intent = new Intent(this, StoryDetailActivity.class);
        intent.putExtra("STORY_ID", storyId);
        startActivity(intent);
    }
}