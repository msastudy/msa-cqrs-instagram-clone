package com.instagram.clone.common.model.event.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@NoArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private String email;
    private String encryptedPassword;
    private String userName;
    private String fullName;
}
