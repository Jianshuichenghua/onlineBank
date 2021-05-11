package mine.web.onlineBank.interfaces.entity;

import mine.web.onlineBank.domain.entity.IdEntity;
import mine.web.onlineBank.interfaces.converter.EntityDTOConverter;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
public abstract class IdEntityConverter<E extends IdEntity, D extends IdEntityDTO> implements EntityDTOConverter<E, D> {

    @Override
    public E toEntity(D dto) {

        E e = entity();
        e.setId(dto.getId());
        e.setCreateAccount(dto.getCreateAccount());
        e.setCreateDate(date(dto.getCreateDate()));
        e.setLastUpdateAccount(dto.getLastUpdateAccount());
        e.setLastUpdateDate(date(dto.getLastUpdateDate()));
        return e;
    }

    @Override
    public List<E> toEntity(List<D> dtos) {

        return dtos.stream().map(d -> toEntity(d)).collect(Collectors.toList());
    }

    @Override
    public D toDTO(E entity) {

        D d = dto();
        d.setId(entity.getId());
        d.setCreateAccount(entity.getCreateAccount());
        d.setCreateDate(time(entity.getCreateDate()));
        d.setLastUpdateAccount(entity.getLastUpdateAccount());
        d.setLastUpdateDate(time(entity.getLastUpdateDate()));
        return d;
    }

    @Override
    public List<D> toDTO(List<E> entities) {

        return entities.stream().map(e -> toDTO(e)).collect(Collectors.toList());
    }

    @Override
    public PageDTO<D> page(Page<E> page) {

        PageDTO<D> pageDTO = new PageDTO<>();
        List<D> dtos = toDTO(page.getContent());
        pageDTO.setResult(dtos);
        pageDTO.setTotalCount(page.getTotalElements());
        return pageDTO;
    }

    private Date date(Long time) {

        return time != null ? new Date(time) : new Date();
    }

    private Long time(Date date) {

        return date != null ? date.getTime() : System.currentTimeMillis();
    }

    protected abstract E entity();

    protected abstract D dto();
}