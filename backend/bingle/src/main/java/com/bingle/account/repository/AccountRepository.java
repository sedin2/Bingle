package com.bingle.account.repository;

import com.bingle.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByKakaoId(Long kakaoId);

    boolean existsByNickname(String nickname);
}
