package com.instagram.clone.account.query.model.dto.response;

import com.instagram.clone.account.query.model.entity.Account;
import com.instagram.clone.account.query.model.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {
    private String accountId;
    private String email;
    private String userName;
    private String fullName;
    private String website;
    private String introduction;
    private Gender gender;
    private String profileUrl;
    private Boolean isPrivate;

    public static GetUserResponse fromEntity(Account account) {
        return GetUserResponse.builder()
                .accountId(account.getAccountId())
                .email(account.getEmail())
                .userName(account.getUserName())
                .fullName(account.getFullName())
                .website(account.getWebsite())
                .introduction(account.getIntroduction())
                .gender(account.getGender())
                .profileUrl(account.getProfileUrl())
                .isPrivate(account.getIsPrivate())
                .build();
    }
}
