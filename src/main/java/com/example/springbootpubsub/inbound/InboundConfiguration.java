package com.example.springbootpubsub.inbound;

import com.example.springbootpubsub.config.PubSubConfiguration;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.AckMode;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class InboundConfiguration {
    private final PubSubConfiguration pubSubConfiguration;

    /**
     * Create a message channel for messages arriving from the subscription.
     * @return
     */
    @Bean
    public MessageChannel inputMessageChannel() {
        return new PublishSubscribeChannel();
    }

    /**
     * Create an inbound channel adapter to listen to the subscription and send
     * messages to the input message channel
     * @param messageChannel
     * @param pubSubTemplate
     * @return
     */
    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("inputMessageChannel") MessageChannel messageChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter =
                new PubSubInboundChannelAdapter(pubSubTemplate, pubSubConfiguration.getSubscription());
        adapter.setOutputChannel(messageChannel);
        adapter.setAckMode(AckMode.MANUAL);
        //adapter.setPayloadType(String.class);
        return adapter;
    }

    /**
     * Define what happens to the messages arriving in the message channel.
     * @param payload
     * @param message
     */
    @ServiceActivator(inputChannel = "inputMessageChannel")
    public void messageReceiver(
            String payload,
            @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
        log.info("Message arrived via an inbound channel adapter Payload: {}", payload);
        message.ack();
    }

}
