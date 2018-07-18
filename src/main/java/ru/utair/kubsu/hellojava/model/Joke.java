package ru.utair.kubsu.hellojava.model;

public class Joke {

    private String site;
    private String category;
    private String jokeText;

    public Joke(String site, String category, String jokeText) {
        this.site = site;
        this.category = category;
        this.jokeText = jokeText;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }
}
