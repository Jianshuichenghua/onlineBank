package mine.web.onlineBank.infrastructure.fund;

import mine.web.onlineBank.domain.fund.FundTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @date 2021/5/11
 * @author xujunfeng
 * @description
 */
public interface FundTransactionLogRepository extends JpaRepository<FundTransactionLog, Long>, JpaSpecificationExecutor<FundTransactionLog> {
}