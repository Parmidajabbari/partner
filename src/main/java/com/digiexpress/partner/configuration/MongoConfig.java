package com.digiexpress.partner.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final MongoDBProperties mongoDBProperties;

    public MongoConfig(MongoDBProperties mongoDBProperties) {
        this.mongoDBProperties = mongoDBProperties;
    }


    @Override
    protected String getDatabaseName() {
        return mongoDBProperties.getDb();
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://" + mongoDBProperties.getUsername() + ":" + mongoDBProperties.getPassword() + "@" + mongoDBProperties.getHost() + ":" + mongoDBProperties.getPort() + "/" + getDatabaseName());
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }
}
