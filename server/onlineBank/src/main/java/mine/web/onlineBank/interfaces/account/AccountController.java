package mine.web.onlineBank.interfaces.account;

import mine.web.onlineBank.application.account.AccountApplicationService;
import mine.web.onlineBank.application.response.ApplicationResponse;
import mine.web.onlineBank.domain.account.Account;
import mine.web.onlineBank.interfaces.response.RestfulResponse;
import mine.web.onlineBank.interfaces.response.RestfulResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/login")
    public RestfulResponse login(@RequestParam String account, @RequestParam String password) {

        ApplicationResponse applicationResponse = accountApplicationService.login(account, password);
        return RestfulResponses.of(applicationResponse);
    }

    @PostMapping("/checkPassword")
    public RestfulResponse checkPassword(@RequestParam String account, @RequestParam String password) {

        ApplicationResponse applicationResponse = accountApplicationService.checkPassword(account, password);
        return RestfulResponses.of(applicationResponse);
    }

    @GetMapping("/findByAccountLike")
    public RestfulResponse findByAccountLike(@RequestParam(required = false) String account) {

        ApplicationResponse applicationResponse = accountApplicationService.findByAccountLike(account);
        List<Account> accounts = (List<Account>) applicationResponse.getBody();
        List<AccountDTO> accountDTOS = accountConverter.toDTO(accounts);
        return RestfulResponses.of(applicationResponse, accountDTOS);
    }
}