package mine.web.onlineBank.domain.enums;


import mine.web.onlineBank.domain.fund.FundTransactionLog.TransactionType;

import java.util.Arrays;
import java.util.List;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 交易类型转化器
 */
public class TransactionTypeConverter extends MyEnumConverter<TransactionType> {

    @Override
    protected List<TransactionType> enums() {

        return Arrays.asList(TransactionType.values());
    }
}