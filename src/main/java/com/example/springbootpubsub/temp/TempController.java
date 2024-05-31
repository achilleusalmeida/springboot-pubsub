package com.example.springbootpubsub.temp;

import com.example.springbootpubsub.config.NotificationConfiguration;
import com.example.springbootpubsub.model.NotificationVO;
import com.example.springbootpubsub.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TempController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notification")
    public void sendMessage(@RequestBody NotificationVO notificationVO){
        notificationService.processNotificationRequest(notificationVO);
    }
}
