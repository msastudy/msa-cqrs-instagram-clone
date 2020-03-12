package com.instagram.clone.account.query.service;

import com.instagram.clone.account.query.model.entity.Account;
import com.instagram.clone.account.query.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountQueryService {

    private final AccountRepository accountRepository;

    public Account getAccountOne(String accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("cannot find account"));
    }
}
