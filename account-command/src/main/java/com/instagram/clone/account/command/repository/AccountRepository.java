package com.instagram.clone.account.command.repository;

import com.instagram.clone.account.command.aggregate.AccountAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountAggregate, String> {
    Optional<AccountAggregate> findByEmail(String email);
    Optional<AccountAggregate> findByUserName(String userName);
}
