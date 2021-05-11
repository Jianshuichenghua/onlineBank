package mine.web.onlineBank.application.fund;

import mine.web.onlineBank.application.account.AccountResponseCodes;
import mine.web.onlineBank.application.response.ApplicationResponse;
import mine.web.onlineBank.application.response.ApplicationResponses;
import mine.web.onlineBank.domain.account.Account;
import mine.web.onlineBank.domain.fund.FundTransactionLog;
import mine.web.onlineBank.infrastructure.account.AccountRepository;
import mine.web.onlineBank.infrastructure.fund.FundTransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2021/5/11
 * @author xujunfeng
 * @description
 */
@Service
public class FundApplicationService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FundTransactionLogRepository repository;

    @Transactional
    public ApplicationResponse deposit(String account, Double amount, String description) {

        List<Account> accounts = accountRepository.findByAccount(account);
        if (!accounts.isEmpty()) {

            Account curAccount = accounts.get(0);
            Double balance = curAccount.getBalance();
            curAccount.setBalance(balance + amount);
            accountRepository.save(curAccount);
            createFundTransactionLog(account, account, amount, FundTransactionLog.TransactionType.DEPOSIT, description);
            return ApplicationResponses.success(curAccount);
        }
        return ApplicationResponses.fail(AccountResponseCodes.ACCOUNT_IS_NOT_EXISTED);
    }

    @Transactional
    public ApplicationResponse withdrawal(String account, Double amount, String description) {

        List<Account> accounts = accountRepository.findByAccount(account);
        if (!accounts.isEmpty()) {

            Account curAccount = accounts.get(0);
            Double balance = curAccount.getBalance();
            if (balance >= amount) {

                curAccount.setBalance(balance - amount);
                accountRepository.save(curAccount);
                createFundTransactionLog(account, account, amount, FundTransactionLog.TransactionType.WITHDRAWAL, description);
                return ApplicationResponses.success(curAccount);
            }
            return ApplicationResponses.fail(FundResponseCodes.NOT_SUFFICIENT_FUNDS);
        }
        return ApplicationResponses.fail(AccountResponseCodes.ACCOUNT_IS_NOT_EXISTED);
    }

    @Transactional
    public ApplicationResponse transfer(String account, String targetAccount, Double amount, String description) {

        List<Account> accounts = accountRepository.findByAccount(account);
        if (accounts.isEmpty()) {

            return ApplicationResponses.fail(AccountResponseCodes.ACCOUNT_IS_NOT_EXISTED);
        }
        List<Account> targetAccounts = accountRepository.findByAccount(targetAccount);
        if (targetAccounts.isEmpty()) {

            return ApplicationResponses.fail(AccountResponseCodes.TARGET_ACCOUNT_IS_NOT_EXISTED);
        }
        Account curAccount = accounts.get(0);
        Account curTargetAccount = targetAccounts.get(0);
        Double balance = curAccount.getBalance();
        if (balance >= amount) {

            curAccount.setBalance(balance - amount);
            curTargetAccount.setBalance(curTargetAccount.getBalance() + amount);
            accountRepository.saveAll(Arrays.asList(curAccount, curTargetAccount));
            createFundTransactionLog(account, targetAccount, amount, FundTransactionLog.TransactionType.TRANSACTION, description);
            return ApplicationResponses.success(composeTransferBody(account, targetAccount, curAccount.getBalance(), curTargetAccount.getBalance()));
        }
        return ApplicationResponses.fail(FundResponseCodes.NOT_SUFFICIENT_FUNDS);
    }

    private Map<String, Double> composeTransferBody(String account, String targetAccount, Double amount, Double targetAmount) {

        Map<String, Double> map = new HashMap<>(2);
        map.put(account, amount);
        map.put(targetAccount, targetAmount);
        return map;
    }

    public ApplicationResponse findAll(String account, Integer pageNo, Integer pageSize) {

        Specification<FundTransactionLog> specification = new Specification<FundTransactionLog>() {

            @Override
            public Predicate toPredicate(Root<FundTransactionLog> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

                Predicate predicate1 = builder.equal(root.get("sourceAccount").as(String.class), account);
                Predicate predicate2 = builder.equal(root.get("targetAccount").as(String.class), account);
                return query.where(builder.or(predicate1, predicate2)).getRestriction();
            }
        };
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<FundTransactionLog> page = repository.findAll(specification, pageable);
        return ApplicationResponses.success(page);
    }

    private void createFundTransactionLog(String account, String targetAccount, Double amount,
                                    FundTransactionLog.TransactionType transactionType, String description) {

        FundTransactionLog log = new FundTransactionLog();
        log.setSourceAccount(account);
        log.setTargetAccount(targetAccount);
        log.setAmount(amount);
        log.setDescription(description);
        log.setTransactionType(transactionType);
        log.setCreateAccount(account);
        log.setLastUpdateAccount(account);
        repository.save(log);
    }
}