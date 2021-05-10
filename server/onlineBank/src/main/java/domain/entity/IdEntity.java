package domain.entity;

import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description Id实体
 */
@MappedSuperclass
public abstract class IdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_account_id")
    private Long createAccountId;

    @Column(name = "create_date")
    private Date createDate = new Date();

    @Column(name = "last_update_account_id")
    private Long lastUpdateAccountId;

    @Column(name = "last_update_date")
    private Date lastUpdateDate = new Date();

    @Column(name = "removed", nullable = false)
    private boolean removed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateAccountId() {
        return createAccountId;
    }

    public void setCreateAccountId(Long createAccountId) {
        this.createAccountId = createAccountId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getLastUpdateAccountId() {
        return lastUpdateAccountId;
    }

    public void setLastUpdateAccountId(Long lastUpdateAccountId) {
        this.lastUpdateAccountId = lastUpdateAccountId;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdEntity idEntity = (IdEntity) o;
        return ObjectUtils.nullSafeEquals(getId(), idEntity.getId());
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(getId());
    }
}