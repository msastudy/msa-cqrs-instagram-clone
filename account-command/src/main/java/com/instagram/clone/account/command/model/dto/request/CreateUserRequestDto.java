package com.instagram.clone.account.command.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CreateUserRequestDto {
    private String email;
    private String password;
    private String userName;
    private String fullName;
}
