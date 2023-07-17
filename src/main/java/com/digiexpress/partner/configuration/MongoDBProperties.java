package com.digiexpress.partner.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.mongo", ignoreUnknownFields = false)
@Getter
@Setter
public class MongoDBProperties {
    private String username;
    private String password;
    private String host;
    private String port;
    private String db;

}
