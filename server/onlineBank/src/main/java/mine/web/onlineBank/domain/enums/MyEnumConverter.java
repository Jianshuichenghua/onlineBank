package mine.web.onlineBank.domain.enums;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 枚举转换器
 */
public abstract class MyEnumConverter<T extends MyEnum> implements AttributeConverter<T, Integer> {

    public Integer convertToDatabaseColumn(T myEnum) {
        return myEnum == null ? null : myEnum.value();
    }

    public T convertToEntityAttribute(Integer integer) {

        T t = enums().stream().filter(e -> e.value().equals(integer)).findFirst().orElse(null);
        return t != null ? t : null;
    }

    protected abstract List<T> enums();
}