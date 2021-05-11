package mine.web.onlineBank.domain.entity;

import mine.web.onlineBank.domain.constant.ColumnConstant;
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

    @Column(name = "create_account", length = ColumnConstant.VARCHAR_30)
    private String createAccount;

    @Column(name = "create_date")
    private Date createDate = new Date();

    @Column(name = "last_update_account", length = ColumnConstant.VARCHAR_30)
    private String lastUpdateAccount;

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

    public String getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public String getLastUpdateAccount() {
        return lastUpdateAccount;
    }

    public void setLastUpdateAccount(String lastUpdateAccount) {
        this.lastUpdateAccount = lastUpdateAccount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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