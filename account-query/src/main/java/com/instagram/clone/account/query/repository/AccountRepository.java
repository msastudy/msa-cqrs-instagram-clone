package com.instagram.clone.account.query.repository;

import com.instagram.clone.account.query.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
