package mine.web.onlineBank.domain.fund;

import mine.web.onlineBank.domain.entity.IdEntity;
import mine.web.onlineBank.domain.enums.MyEnum;
import mine.web.onlineBank.domain.enums.TransactionTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

import mine.web.onlineBank.domain.constant.ColumnConstant;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 资金交易日志
 */
@Entity
@Table(name = "fund_transaction_log")
public class FundTransactionLog extends IdEntity {

    @Column(name = "source_account", length = ColumnConstant.VARCHAR_30)
    private String sourceAccount;

    @Column(name = "target_account", length = ColumnConstant.VARCHAR_30)
    private String targetAccount;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "transaction_type")
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType transactionType;

    @Column(name = "description")
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

    public static enum TransactionType implements MyEnum {

        TRANSACTION(1), DEPOSIT(2), WITHDRAWAL(3);

        private int value;

        TransactionType(int value) {

            this.value = value;
        }

        public Integer value() {

            return value;
        }

        public static List<TransactionType> enums() {

            return Arrays.asList(values());
        }
    }
}