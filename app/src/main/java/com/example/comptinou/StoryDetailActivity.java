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
        } else if (getIntent().hasExtra("HERO_NAME")) {
            String heroName = getIntent().getStringExtra("HERO_NAME");
            // Générer une nouvelle histoire avec ce héros
            generateStory(heroName);
        }
    }

    private void loadStoryDetails(String storyId) {
        // Dans une application réelle, ces données viendraient d'une base de données
        if ("1".equals(storyId)) {
            storyDetailTitle.setText("L'aventure de Sophie");
            storyDetailContent.setText("Il était une fois une petite fille nommée Sophie qui adorait explorer le monde. Un jour, alors qu'elle se promenait dans la forêt près de chez elle, elle découvrit un petit sentier qu'elle n'avait jamais remarqué auparavant.\n\nCurieuse, Sophie décida de suivre ce sentier. Au bout du chemin, elle trouva une petite cabane en bois. Elle frappa à la porte, mais personne ne répondit. Prudemment, elle poussa la porte qui s'ouvrit avec un grincement.\n\nÀ l'intérieur, tout était miniature, comme si la maison appartenait à des elfes. Sur une petite table, elle trouva un message: \"Bienvenue, voyageur. Pour continuer ton aventure, tu dois résoudre cette énigme...\"\n\nSophie adora cette découverte et revint souvent dans la cabane pour imaginer toutes sortes d'aventures fantastiques avec ses nouveaux amis imaginaires.");
        } else if ("2".equals(storyId)) {
            storyDetailTitle.setText("Le voyage de Lucas");
            storyDetailContent.setText("Lucas était un petit garçon très curieux. Il rêvait de voyager dans l'espace. Un soir, alors qu'il regardait les étoiles depuis sa fenêtre, une lumière étrange apparut dans le ciel.\n\nCette lumière se rapprocha et Lucas réalisa qu'il s'agissait d'un petit vaisseau spatial. Le vaisseau atterrit dans le jardin et une porte s'ouvrit. Un petit être bleu avec de grands yeux en sortit et fit signe à Lucas de le rejoindre.\n\nN'écoutant que son courage, Lucas descendit dans le jardin. L'extraterrestre se présenta: \"Je m'appelle Zorg, et je viens de la planète Nebulosa. J'ai besoin de ton aide pour retrouver mon chemin.\"\n\nC'est ainsi que commença la plus grande aventure de Lucas. Ensemble, Lucas et Zorg parcoururent les étoiles, visitèrent des planètes incroyables et rencontrèrent des créatures fascinantes. Mais à la fin de leur voyage, Lucas comprit que les plus grandes aventures étaient celles qu'on vivait avec le cœur.");
        } else {
            // Histoire non trouvée
            storyDetailTitle.setText("Histoire non trouvée");
            storyDetailContent.setText("Désolé, cette histoire n'existe pas ou a été supprimée.");
        }
    }

    private void generateStory(String heroName) {
        // Génération simple d'une histoire basée sur le nom du héros
        storyDetailTitle.setText("L'aventure de " + heroName);
        storyDetailContent.setText("Il était une fois " + heroName + ", un enfant extraordinaire qui vivait dans un petit village entouré de montagnes majestueuses.\n\n" +
                "Un jour, alors que " + heroName + " jouait près de la rivière, un petit animal étrange apparut. Il avait des ailes colorées, une queue brillante et de grands yeux curieux.\n\n" +
                "\"Bonjour,\" dit l'animal. \"Je m'appelle Lumino et je viens du royaume des rêves. J'ai besoin de ton aide!\"\n\n" +
                "Sans hésiter, " + heroName + " accepta d'aider Lumino. Ensemble, ils traversèrent des forêts enchantées, escaladèrent des collines de nuages et naviguèrent sur des rivières arc-en-ciel.\n\n" +
                "Grâce à son courage et sa gentillesse, " + heroName + " aida Lumino à retrouver le chemin du royaume des rêves et devint un héros pour tous ses habitants.\n\n" +
                "De retour chez lui, " + heroName + " se rendit compte que la plus grande magie était celle qu'on portait en soi: la bonté, le courage et l'imagination.");
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