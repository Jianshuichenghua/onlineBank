package mine.web.onlineBank.interfaces.account;

import mine.web.onlineBank.domain.account.Account;
import mine.web.onlineBank.interfaces.entity.IdEntityConverter;
import org.springframework.stereotype.Component;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
@Component
public class AccountConverter extends IdEntityConverter<Account, AccountDTO> {

    @Override
    public Account toEntity(AccountDTO dto) {

        if (dto != null) {

            Account account = super.toEntity(dto);
            account.setAccount(dto.getAccount());
            account.setPassword(dto.getPassword());
            account.setFirstName(dto.getFirstName());
            account.setLastName(dto.getLastName());
            account.setCompany(dto.isCompany());
            account.setEmail(dto.getEmail());
            account.setBalance(dto.getBalance());
            return account;
        }
        return null;
    }

    @Override
    public AccountDTO toDTO(Account entity) {

        if (entity != null) {

            AccountDTO dto = super.toDTO(entity);
            dto.setAccount(entity.getAccount());
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setCompany(entity.isCompany());
            dto.setEmail(entity.getEmail());
            dto.setBalance(entity.getBalance());
            return dto;
        }
        return null;
    }

    @Override
    protected Account entity() {

        return new Account();
    }

    @Override
    protected AccountDTO dto() {

        return new AccountDTO();
    }
}