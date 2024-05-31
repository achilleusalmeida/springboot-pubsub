package com.example.springbootpubsub.service;

import com.example.springbootpubsub.config.NotificationConfiguration;
import com.example.springbootpubsub.model.NotificationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.springbootpubsub.enums.NotificationType.EMAIL;
import static com.example.springbootpubsub.enums.NotificationType.SMS;

@Service
@Slf4j
public class NotificationService {

    @Autowired
    private NotificationConfiguration notificationConfiguration;

    public void processNotificationRequest(NotificationVO notificationVO) {
        if(SMS.name().equals(notificationVO.getNotificationType())){
            log.info("Processing sms request");
        }else if(EMAIL.name().equals(notificationVO.getNotificationType())){
            log.info("Processing email request");
        }
    }
}
