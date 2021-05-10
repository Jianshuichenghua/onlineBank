package domain.fund;

import domain.entity.IdEntity;
import domain.enums.MyEnum;
import domain.enums.MyEnumConverter;
import domain.enums.TransactionTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 资金交易日志
 */
@Entity
@Table(name = "fund_transaction_log")
public class FundTransactionLog extends IdEntity {

    @Column(name = "source_account_id")
    private Long sourceAccountId;

    @Column(name = "target_account_id")
    private Long targetAccountId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "transaction_type")
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType transactionType;

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