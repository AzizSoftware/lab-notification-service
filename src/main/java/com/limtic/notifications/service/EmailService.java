package com.limtic.notifications.service;

import com.limtic.notifications.dto.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String ADMIN_EMAIL = "zouaghiaziz122@gmail.com";

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("zouaghiaziz122@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("Email sent to " + to);
    }

    public void sendNewUploadNotification(KafkaMessage notification) {
        String subject = "New File Uploaded: " + notification.getEmail();
        String body = "Hello users, A new file has been uploaded by " + notification.getEmail() + ".\n" +
                      "File details: Title: " + notification.getMessage();

        // Use the recipientEmails list from the KafkaMessage
        if (notification.getRecipientEmails() != null) {
            String[] recipientEmailsArray = notification.getRecipientEmails().toArray(new String[0]);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("zouaghiaziz122@gmail.com");
            message.setTo(recipientEmailsArray);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            System.out.println("Broadcast email sent to " + notification.getRecipientEmails().size() + " users.");
        }
    }

    public void sendAdminNewSignupNotification(KafkaMessage notification) {
        String subject = "New User Signup Alert";
        String body = "A new user has signed up:\n" +
                      "Email: " + notification.getEmail() + "\n" +
                      "Please approve or decline the account.";
        sendEmail(ADMIN_EMAIL, subject, body);
    }

    public void sendUserApprovalNotification(KafkaMessage notification) {
        String subject = "Your Account Has Been Approved!";
        String body = "Dear " + notification.getEmail() + ",\n\n" +
                      "Your request has been approved.\n" +
                      "You can now log in using your email and password: " + notification.getPassword();
        sendEmail(notification.getEmail(), subject, body);
    }
}