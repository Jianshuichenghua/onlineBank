package mine.web.onlineBank.interfaces.converter;

import mine.web.onlineBank.domain.entity.IdEntity;
import mine.web.onlineBank.interfaces.entity.IdEntityDTO;
import mine.web.onlineBank.interfaces.entity.PageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 实体与DTO对象转换器
 */
public interface EntityDTOConverter<E extends IdEntity, D extends IdEntityDTO> {

    E toEntity(D dto);

    D toDTO(E entity);

    List<E> toEntity(List<D> dtos);

    List<D> toDTO(List<E> entities);

    PageDTO<D> page(Page<E> page);
}