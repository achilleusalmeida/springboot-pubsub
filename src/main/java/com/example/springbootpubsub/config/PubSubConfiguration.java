package com.example.springbootpubsub.config;

import com.example.springbootpubsub.factory.YamlPropertySourceFactory;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "gcp.pubsub")
@PropertySource(value = "classpath:config/pubsub.yml", factory = YamlPropertySourceFactory.class)
public class PubSubConfiguration {
    private String topic;
    private String subscription;
}
