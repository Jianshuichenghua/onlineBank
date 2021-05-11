package mine.web.onlineBank.infrastructure.account;

import mine.web.onlineBank.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByAccount(String account);

    List<Account> findByAccountLike(String account);
}