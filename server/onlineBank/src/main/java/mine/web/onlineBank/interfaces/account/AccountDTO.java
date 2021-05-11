package mine.web.onlineBank.interfaces.account;

import mine.web.onlineBank.interfaces.entity.IdEntityDTO;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
public class AccountDTO extends IdEntityDTO {

    private String account;

    private String password;

    private String firstName;

    private String lastName;

    private boolean isCompany;

    private String email;

    private Double balance = 0.0;

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