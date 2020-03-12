package com.instagram.clone.account.query.controller;

import com.instagram.clone.account.query.model.dto.response.GetUserResponse;
import com.instagram.clone.account.query.model.entity.Account;
import com.instagram.clone.account.query.service.AccountQueryService;
import com.instagram.clone.common.model.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.instagram.clone.common.security.JwtInterceptor.ACCOUNT_ID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final AccountQueryService accountQueryService;

    @GetMapping
    public ResponseEntity<ApiResult<GetUserResponse>> getMe(@RequestAttribute(ACCOUNT_ID) String accountId) {
        Account account = accountQueryService.getAccountOne(accountId);
        return ResponseEntity.ok().body(ApiResult.OK(GetUserResponse.fromEntity(account)));
    }
}
