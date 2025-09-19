package com.limtic.notifications.service;


import com.limtic.notifications.dto.UserApprovalNotification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "notifications", groupId = "notification-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(UserApprovalNotification notification) {
        System.out.println("Received notification: " + notification);
        // Here we will call EmailService to send the actual email
        // emailService.send(notification);
    }
}
