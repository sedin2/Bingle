package com.bingle.account.service;

import com.bingle.account.model.Account;
import com.bingle.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
