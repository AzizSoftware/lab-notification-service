package com.limtic.notifications.service;

import com.limtic.notifications.dto.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private EmailService emailService; // Inject the EmailService

    @KafkaListener(topics = "notifications", groupId = "notification-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(KafkaMessage message) {
        System.out.println("Received message of type: " + message.getType());

        switch (message.getType()) {
            case "FILE_UPLOADED":
                System.out.println("Processing file upload notification.");
                emailService.sendNewUploadNotification(message);
                break;
            case "NEW_USER_SIGNUP":
                System.out.println("Processing new user signup notification.");
                emailService.sendAdminNewSignupNotification(message);
                break;
            case "USER_APPROVED":
                System.out.println("Processing user approval notification.");
                emailService.sendUserApprovalNotification(message);
                break;
            case "USER_DECLINED":
                System.out.println("Processing user declined notification.");
                // Add a method to send a decline email if needed
                // emailService.sendUserDeclineNotification(message);
                break;
            default:
                System.out.println("Unknown message type received.");
                break;
        }
    }
}