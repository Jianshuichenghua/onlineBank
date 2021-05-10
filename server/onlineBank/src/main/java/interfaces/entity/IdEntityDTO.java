package interfaces.entity;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
public class IdEntityDTO {

    private Long id;

    private Long createAccountId;

    private Long createDate;

    private Long lastUpdateAccountId;

    private Long lastUpdateDate;

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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getLastUpdateAccountId() {
        return lastUpdateAccountId;
    }

    public void setLastUpdateAccountId(Long lastUpdateAccountId) {
        this.lastUpdateAccountId = lastUpdateAccountId;
    }

    public Long getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Long lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}