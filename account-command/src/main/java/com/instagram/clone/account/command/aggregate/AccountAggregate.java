package com.instagram.clone.account.command.aggregate;

import com.instagram.clone.account.command.model.AccountStatus;
import com.instagram.clone.account.command.model.Gender;
import com.instagram.clone.common.model.command.account.CreateAccountCommand;
import com.instagram.clone.common.model.event.account.AccountCreatedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity()
@Table(name = "t_account_aggregate")
public class AccountAggregate {

    @AggregateIdentifier
    @Id
    @Column(name = "account_id", unique = true, nullable = false)
    private String accountId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "full_name", unique = true)
    private String fullName;

    @Column(name = "website")
    private String website;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.UNKNOWN;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "email_verification", nullable = false)
    private Boolean emailVerification = false;

    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate = false;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        log.info("handle CreateAccountCommand : {}", command);
        this.password = command.getEncryptedPassword();
        this.email = command.getEmail();
        this.userName = command.getUserName();
        this.fullName = command.getFullName();

        apply(AccountCreatedEvent.builder()
                .accountId(command.getAccountId())
                .email(command.getEmail())
                .encryptedPassword(command.getEncryptedPassword())
                .userName(command.getUserName())
                .fullName(command.getFullName())
                .build());
    }

    @EventSourcingHandler
    protected void handleEventSourcing(AccountCreatedEvent event) {
        log.info("handle AccountCreatedEvent : {}", event);
//        this.accountId = event.getAccountId();
//        this.password = event.getEncryptedPassword();
//        this.email = event.getEmail();
//        this.userName = event.getUserName();
//        this.fullName = event.getFullName();
    }
}


