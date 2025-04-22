package com.example.comptinou;

public class Story {
    private String id;
    private String title;
    private String content;
    private String preview;
    private String date;
    private String heroName;

    // Constructeurs, getters et setters

    public Story(String id, String title, String content, String date, String heroName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.heroName = heroName;

        // Générer un aperçu du contenu (premiers caractères)
        if (content.length() > 100) {
            this.preview = content.substring(0, 100) + "...";
        } else {
            this.preview = content;
        }
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getPreview() { return preview; }
    public String getDate() { return date; }
    public String getHeroName() { return heroName; }
}