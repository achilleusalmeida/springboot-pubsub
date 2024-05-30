package com.example.springbootpubsub.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "gcp.pubsub")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PubSubConfiguration {
    private String topic;
    private String subscription;
}
