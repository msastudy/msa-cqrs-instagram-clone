package com.instagram.clone.account.command.controller;

import com.instagram.clone.account.command.model.dto.request.CreateUserRequestDto;
import com.instagram.clone.account.command.model.dto.response.TokenResponseDto;
import com.instagram.clone.account.command.service.CheckAccountDuplicatedService;
import com.instagram.clone.account.command.service.CreateAccountService;
import com.instagram.clone.account.command.service.JWTService;
import com.instagram.clone.common.model.api.ApiResult;
import com.instagram.clone.common.model.security.model.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final CreateAccountService createAccountService;
    private final CheckAccountDuplicatedService checkAccountDuplicatedService;
    private final JWTService jwtService;

    @PostMapping
    public ResponseEntity<ApiResult<TokenResponseDto>> createUser(@RequestBody CreateUserRequestDto requestDto) throws Exception {

        // check email duplicated
        if (checkAccountDuplicatedService.isEmailDuplicated(requestDto.getEmail())) {
            throw new Exception("email is duplicated");
        }

        // check username duplicated
        if (checkAccountDuplicatedService.isUserNameDuplicated(requestDto.getUserName())) {
            throw new Exception("username is duplicated");
        }

        // create user
        String accountId = createAccountService.create(requestDto);

        // make jwt Token with userId
        String jwtToken = jwtService.generate(accountId,
                requestDto.getUserName(),
                null,
                new String[]{Role.USER.value()});
        return ResponseEntity.ok().body(ApiResult.OK(new TokenResponseDto(jwtToken)));
    }
}
