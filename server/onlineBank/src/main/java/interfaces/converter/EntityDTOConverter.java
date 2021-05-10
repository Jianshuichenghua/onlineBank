package interfaces.converter;

import domain.entity.IdEntity;
import interfaces.entity.IdEntityDTO;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 实体与DTO对象转换器
 */
public interface EntityDTOConverter<E extends IdEntity, D extends IdEntityDTO> {

    E toEntity(D dto);

    D toDTO(E entity);
}