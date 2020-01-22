package com.gym.tracker.configuration;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class MongoDbConfiguration {

	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Bean
	public MongoDbFactory getMongoDbFactory() throws UnknownHostException {
		return new SimpleMongoDbFactory(new MongoClient(new MongoClientURI(mongoUri)), database);
	}

	@Bean(name = "mongoTemplate")
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		return new MongoTemplate(getMongoDbFactory());
	}
}
