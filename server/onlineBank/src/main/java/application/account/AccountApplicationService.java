package application.account;

import application.response.ApplicationResponse;
import application.response.ApplicationResponses;
import domain.account.Account;
import infrastructure.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional
public class AccountApplicationService {

    @Autowired
    private AccountRepository accountRepository;

    public ApplicationResponse register(Account account) {

        if (StringUtils.hasText(account.getAccount())) {

            List<Account> accounts = accountRepository.findByAccount(account.getAccount());
            if (accounts.isEmpty()) {

                accountRepository.save(account);
                return ApplicationResponses.success(account);
            }
            return ApplicationResponses.fail(AccountApplicationReponseCodes.ACCOUNT_IS_EXISTED, account);
        }
        return ApplicationResponses.fail(AccountApplicationReponseCodes.ACCOUNT_IS_BLANK, account);
    }
}