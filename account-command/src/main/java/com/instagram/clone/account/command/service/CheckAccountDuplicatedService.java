package com.instagram.clone.account.command.service;

import com.instagram.clone.account.command.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CheckAccountDuplicatedService {

    private final AccountRepository accountRepository;

    public boolean isEmailDuplicated(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }

    public boolean isUserNameDuplicated(String userName) {
        return accountRepository.findByUserName(userName).isPresent();
    }
}
