package mine.web.onlineBank.application.account;

import mine.web.onlineBank.application.response.ApplicationResponse;
import mine.web.onlineBank.application.response.ApplicationResponses;
import mine.web.onlineBank.domain.account.Account;
import mine.web.onlineBank.infrastructure.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 
 */
@Service
public class AccountApplicationService {

    @Autowired
    private AccountRepository repository;

    @Transactional
    public ApplicationResponse register(Account account) {

        if (StringUtils.hasText(account.getAccount())) {

            List<Account> accounts = repository.findByAccount(account.getAccount());
            if (accounts.isEmpty()) {

                account.setCreateAccount(account.getAccount());
                account.setLastUpdateAccount(account.getAccount());
                repository.save(account);
                return ApplicationResponses.success(account);
            }
            return ApplicationResponses.fail(AccountResponseCodes.ACCOUNT_IS_EXISTED, account);
        }
        return ApplicationResponses.fail(AccountResponseCodes.ACCOUNT_IS_BLANK, account);
    }

    public ApplicationResponse login(String account, String password) {

        return checkPassword(account, password);
    }

    public ApplicationResponse checkPassword(String account, String password) {

        if (StringUtils.hasText(account)) {

            List<Account> accounts = repository.findByAccount(account);
            if (!accounts.isEmpty()) {

                if (accounts.stream().anyMatch(a -> a.getPassword().equals(password))) {

                    return ApplicationResponses.success();
                }
                return ApplicationResponses.fail(AccountResponseCodes.PASSWORD_IS_WRONG);
            }
            return ApplicationResponses.fail(AccountResponseCodes.ACCOUNT_IS_NOT_EXISTED);
        }
        return ApplicationResponses.fail(AccountResponseCodes.ACCOUNT_IS_BLANK);
    }

    public ApplicationResponse findByAccountLike(String account) {

        List<Account> accounts = repository.findByAccountLike("%" + account + "%");
        return ApplicationResponses.success(accounts);
    }
}