package com.instagram.clone.account.command.service;

import com.instagram.clone.account.command.model.dto.request.CreateUserRequestDto;
import com.instagram.clone.account.command.repository.AccountRepository;
import com.instagram.clone.common.model.command.account.CreateAccountCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CreateAccountService {

    private final CommandGateway commandGateway;
    private final PasswordEncoder passEncoder;

    public String create(CreateUserRequestDto dto) throws Exception {

        String accountId = UUID.randomUUID().toString();
        String encryptedPassword = passEncoder.encode(dto.getPassword());

        // emit command
        try {
            CreateAccountCommand createAccountCommand = CreateAccountCommand.builder()
                    .accountId(accountId)
                    .email(dto.getEmail())
                    .encryptedPassword(encryptedPassword)
                    .userName(dto.getUserName())
                    .fullName(dto.getFullName())
                    .build();
            log.info("create command: {}", createAccountCommand);
            commandGateway.sendAndWait(createAccountCommand);
        } catch (CommandExecutionException e) {
            log.error("error : ", e);
            throw new Exception("failed to command CreateAccount");
        }

        return accountId;
    }
}
