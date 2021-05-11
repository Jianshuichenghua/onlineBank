package mine.web.onlineBank.domain.account;

import mine.web.onlineBank.domain.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import mine.web.onlineBank.domain.constant.ColumnConstant;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description 账号
 */
@Entity
@Table(name = "account")
public class Account extends IdEntity {

    @Column(name = "account", length = ColumnConstant.VARCHAR_30)
    private String account;

    @Column(name = "password", length = ColumnConstant.VARCHAR_45)
    private String password;

    @Column(name = "first_name", length = ColumnConstant.VARCHAR_30)
    private String firstName;

    @Column(name = "last_name", length = ColumnConstant.VARCHAR_30)
    private String lastName;

    @Column(name = "is_company")
    private boolean isCompany;

    @Column(name = "email", length = ColumnConstant.VARCHAR_45)
    private String email;

    @Column(name = "balance")
    private Double balance;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transient
    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean company) {
        isCompany = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}