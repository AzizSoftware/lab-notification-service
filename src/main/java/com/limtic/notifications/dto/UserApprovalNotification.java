package com.limtic.notifications.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserApprovalNotification {
    private String email;
    private String event;   // e.g., USER_APPROVED, USER_DECLINED
    private String message; // The email message content
    private LocalDate date;
}
