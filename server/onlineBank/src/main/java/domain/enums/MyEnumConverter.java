package domain.enums;

import javax.persistence.AttributeConverter;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 枚举转换器
 */
public class MyEnumConverter<T extends MyEnum> implements AttributeConverter<T, Integer> {

    public Integer convertToDatabaseColumn(T myEnum) {
        return myEnum == null ? null : myEnum.value();
    }

    public T convertToEntityAttribute(Integer integer) {

        MyEnum myEnum = T.enums().stream().filter(e -> e.value().equals(integer)).findFirst().orElse(null);
        return myEnum != null ? (T) myEnum : null;
    }
}