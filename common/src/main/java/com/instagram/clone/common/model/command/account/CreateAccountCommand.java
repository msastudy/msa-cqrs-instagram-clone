package com.instagram.clone.common.model.command.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class CreateAccountCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private String email;
    private String encryptedPassword;
    private String userName;
    private String fullName;
}
