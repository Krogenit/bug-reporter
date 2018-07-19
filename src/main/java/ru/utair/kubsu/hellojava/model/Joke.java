package ru.utair.kubsu.hellojava.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jokes")
public class Joke {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    ObjectId id;
    private String site;
    private String category;
    private String jokeText;

    public Joke(String site, String category, String jokeText) {
        this.site = site;
        this.category = category;
        this.jokeText = jokeText;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Joke{" +
                "id=" + id +
                ", site='" + site + '\'' +
                ", category='" + category + '\'' +
                ", jokeText='" + jokeText + '\'' +
                '}';
    }
}
