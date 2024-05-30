package com.example.springbootpubsub.outbound;


import com.example.springbootpubsub.config.PubSubConfiguration;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class OutboundConfiguration {
    private final PubSubConfiguration pubSubConfiguration;
    @Bean
    @ServiceActivator(inputChannel = "pubsubOutputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        PubSubMessageHandler adapter = new PubSubMessageHandler(pubsubTemplate, pubSubConfiguration.getTopic());
        adapter.setSuccessCallback(
                ((ackId, message) ->
                        log.info("Message was sent via the outbound channel adapter to pubsub-test-topic!")));

        adapter.setFailureCallback(
                (cause, message) -> log.info("Error sending " + message + " due to " + cause));

        return adapter;
    }

    @MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
    public interface PubSubOutboundGateway {
        void sendToPubSub(String text);
    }
}
