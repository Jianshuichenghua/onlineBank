package mine.web.onlineBank.interfaces.fund;

import mine.web.onlineBank.domain.fund.FundTransactionLog.TransactionType;
import mine.web.onlineBank.interfaces.entity.IdEntityDTO;

/**
 * @date 2021/5/11
 * @author xujunfeng
 * @description
 */
public class FundTransactionLogDTO extends IdEntityDTO {

    private String sourceAccount;

    private String targetAccount;

    private Double amount;

    private TransactionType transactionType;

    private String description;

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}