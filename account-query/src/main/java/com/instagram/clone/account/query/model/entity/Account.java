package com.instagram.clone.account.query.model.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "t_account")
public class Account {
    @Id
    @Column(name = "account_id", unique = true, nullable = false)
    private String accountId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

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
}
