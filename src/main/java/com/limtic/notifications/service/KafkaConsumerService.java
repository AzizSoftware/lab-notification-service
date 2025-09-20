package com.limtic.notifications.service;

import com.limtic.notifications.dto.KafkaMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "notifications", groupId = "notification-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(KafkaMessage message) {
        System.out.println("Received message of type: " + message.getType());
        
        switch (message.getType()) {
            case "USER_APPROVED":
                System.out.printf("User Approved: Email=%s, Password=%s\n", message.getEmail(), message.getPassword());
                // Logic to send approval email
                break;
            case "USER_DECLINED":
                System.out.printf("User Declined: Email=%s\n", message.getEmail());
                // Logic to send decline email
                break;
            case "NEW_USER_SIGNUP":
                System.out.printf("New User Signup: Email=%s, Message=%s\n", message.getEmail(), message.getMessage());
                // Logic to send signup verification email
                break;
            case "FILE_UPLOADED":
                System.out.printf("File Uploaded: Email=%s\n", message.getEmail());
                // Logic for file upload notification
                break;
            default:
                System.out.println("Unknown message type received.");
                break;
        }
    }
}