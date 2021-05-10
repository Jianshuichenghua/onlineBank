package interfaces.entity;

import domain.entity.IdEntity;
import interfaces.converter.EntityDTOConverter;

import java.util.Date;

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
        e.setCreateAccountId(dto.getCreateAccountId());
        e.setCreateDate(date(dto.getCreateDate()));
        e.setLastUpdateAccountId(dto.getLastUpdateAccountId());
        e.setLastUpdateDate(date(dto.getLastUpdateDate()));
        return e;
    }

    @Override
    public D toDTO(E entity) {

        D d = dto();
        d.setId(entity.getId());
        d.setCreateAccountId(entity.getCreateAccountId());
        d.setCreateDate(time(entity.getCreateDate()));
        d.setLastUpdateAccountId(entity.getLastUpdateAccountId());
        d.setLastUpdateDate(time(entity.getLastUpdateDate()));
        return d;
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