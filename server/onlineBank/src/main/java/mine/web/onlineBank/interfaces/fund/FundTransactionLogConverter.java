package mine.web.onlineBank.interfaces.fund;

import mine.web.onlineBank.domain.fund.FundTransactionLog;
import mine.web.onlineBank.interfaces.entity.IdEntityConverter;
import org.springframework.stereotype.Component;

/**
 * @date 2021/5/11
 * @author xujunfeng
 * @description
 */
@Component
public class FundTransactionLogConverter extends IdEntityConverter<FundTransactionLog, FundTransactionLogDTO> {

    @Override
    public FundTransactionLog toEntity(FundTransactionLogDTO dto) {

        if (dto != null) {

            FundTransactionLog log = super.toEntity(dto);
            log.setSourceAccount(dto.getSourceAccount());
            log.setTargetAccount(dto.getTargetAccount());
            log.setAmount(dto.getAmount());
            log.setTransactionType(dto.getTransactionType());
            log.setDescription(dto.getDescription());
            return log;
        }
        return null;
    }

    @Override
    public FundTransactionLogDTO toDTO(FundTransactionLog log) {

        if (log != null) {

            FundTransactionLogDTO dto = super.toDTO(log);
            dto.setSourceAccount(log.getSourceAccount());
            dto.setTargetAccount(log.getTargetAccount());
            dto.setAmount(log.getAmount());
            dto.setTransactionType(log.getTransactionType());
            dto.setDescription(log.getDescription());
            return dto;
        }
        return null;
    }

    @Override
    protected FundTransactionLog entity() {

        return new FundTransactionLog();
    }

    @Override
    protected FundTransactionLogDTO dto() {

        return new FundTransactionLogDTO();
    }
}