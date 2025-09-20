package com.limtic.notifications.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaMessage {
    private String type;
    private String email;
    private String event;
    private String timestamp;
    private String password;
    private String createdAt;
    private String message;
}