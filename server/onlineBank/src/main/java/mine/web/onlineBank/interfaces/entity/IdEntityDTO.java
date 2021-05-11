package mine.web.onlineBank.interfaces.entity;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
public class IdEntityDTO {

    private Long id;

    private String createAccount;

    private Long createDate;

    private String lastUpdateAccount;

    private Long lastUpdateDate;

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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Long lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}