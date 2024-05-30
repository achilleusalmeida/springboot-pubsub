package com.example.springbootpubsub.outbound;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
@RequiredArgsConstructor
@Slf4j
public class OutboundRestController {
    private final OutboundConfiguration.PubSubOutboundGateway messagingGateway;

    @PostMapping("/")
    public void sendMessage(@RequestBody String message){
        log.info("Send this message to outbound channel {}", message);
        messagingGateway.sendToPubSub(message);
    }
}
