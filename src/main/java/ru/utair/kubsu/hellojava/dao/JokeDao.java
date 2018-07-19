package ru.utair.kubsu.hellojava.dao;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ru.utair.kubsu.hellojava.model.Joke;

import java.util.List;

@Repository
public class JokeDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Joke> getAllJokes() {
        return mongoTemplate.findAll(Joke.class);
    }

    public void addJoke(Joke joke) {
        mongoTemplate.insert(joke);
    }

}
