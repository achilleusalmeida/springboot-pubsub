package com.example.springbootpubsub.config;

import com.example.springbootpubsub.factory.YamlPropertySourceFactory;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "notifications")
@PropertySource(value = "classpath:config/notifications.yml", factory = YamlPropertySourceFactory.class)
@ToString
public class NotificationConfiguration {
    private Map<String,Map<String,String>> sms;
}
