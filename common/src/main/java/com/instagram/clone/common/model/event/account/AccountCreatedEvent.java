package com.instagram.clone.common.model.event.account;

import lombok.*;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private String email;
    private String encryptedPassword;
    private String userName;
    private String fullName;
}
