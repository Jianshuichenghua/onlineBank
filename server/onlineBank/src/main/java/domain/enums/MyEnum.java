package domain.enums;

import java.util.List;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 枚举
 */
public interface MyEnum {

    Integer value();

    static List<? extends MyEnum> enums() {

        return null;
    }
}