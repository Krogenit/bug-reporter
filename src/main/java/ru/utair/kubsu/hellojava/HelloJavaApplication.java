package ru.utair.kubsu.hellojava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.client.RestTemplate;
import ru.utair.kubsu.hellojava.model.Joke;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class HelloJavaApplication {

	@Value("${mongo.uri}")
	private String mongoUri;

	@Value("${mongo.db}")
	private String databaseName;

	public static void main(String[] args) {
		SpringApplication.run(HelloJavaApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {

		//RestTemplate temp = new RestTemplate();

		RestTemplate template = builder.build();

		return template;
	}

	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		MongoClientURI uri = new MongoClientURI(mongoUri);

		MongoClient mongoClient = new MongoClient(uri);
		return new SimpleMongoDbFactory(mongoClient, databaseName);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	@Bean
	public Cache<ObjectId, Joke> cache() {
		return CacheBuilder.newBuilder()
				.maximumSize(1000)
				.expireAfterWrite(1, TimeUnit.DAYS)
				.build();
	}
}
