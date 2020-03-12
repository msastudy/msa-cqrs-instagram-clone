package com.instagram.clone.account.query.projection;

import com.instagram.clone.account.query.model.entity.Account;
import com.instagram.clone.account.query.model.entity.AccountStatus;
import com.instagram.clone.account.query.model.entity.Gender;
import com.instagram.clone.account.query.repository.AccountRepository;
import com.instagram.clone.common.model.event.account.AccountCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional
@ProcessingGroup("account")
public class AccountProjection {

    private final AccountRepository accountRepository;

    @EventHandler
    public void handle(AccountCreatedEvent event) {
        log.info("AccountCreatedEvent: {}", event);
        Account account = Account.builder()
                .accountId(event.getAccountId())
                .email(event.getEmail())
                .userName(event.getUserName())
                .fullName(event.getFullName())
                .emailVerification(false)
                .isPrivate(false)
                .status(AccountStatus.ACTIVE)
                .gender(Gender.UNKNOWN)
                .build();
        accountRepository.save(account);
    }
}
