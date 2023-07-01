package com.bingle.account.repository;

import com.bingle.account.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

}
