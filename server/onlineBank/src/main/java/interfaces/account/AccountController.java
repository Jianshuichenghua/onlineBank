package interfaces.account;

import application.account.AccountApplicationService;
import application.response.ApplicationResponse;
import domain.account.Account;
import interfaces.response.RestfulResponse;
import interfaces.response.RestfulResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private AccountApplicationService accountApplicationService;

    @PostMapping("/register")
    public RestfulResponse register(@RequestBody AccountDTO accountDTO) {

        Account account = accountConverter.toEntity(accountDTO);
        ApplicationResponse applicationResponse = accountApplicationService.register(account);
        Account result = (Account) applicationResponse.getBody();
        AccountDTO resultDTO = accountConverter.toDTO(result);
        return RestfulResponses.of(applicationResponse, resultDTO);
    }
}