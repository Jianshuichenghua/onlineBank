package mine.web.onlineBank.interfaces.fund;

import mine.web.onlineBank.application.account.AccountApplicationService;
import mine.web.onlineBank.application.fund.FundApplicationService;
import mine.web.onlineBank.application.response.ApplicationResponse;
import mine.web.onlineBank.domain.account.Account;
import mine.web.onlineBank.domain.fund.FundTransactionLog;
import mine.web.onlineBank.interfaces.account.AccountConverter;
import mine.web.onlineBank.interfaces.entity.PageDTO;
import mine.web.onlineBank.interfaces.response.RestfulResponse;
import mine.web.onlineBank.interfaces.response.RestfulResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @date 2021/5/11
 * @author xujunfeng
 * @description
 */
@RestController
@RequestMapping("/fund")
public class FundController {

    @Autowired
    private FundApplicationService applicationService;

    @Autowired
    private AccountApplicationService accountApplicationService;

    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private FundTransactionLogConverter fundTransactionLogConverter;

    @PostMapping("/deposit")
    public RestfulResponse deposit(@RequestParam String account, @RequestParam Double amount,
            @RequestParam(required = false) String description, @RequestParam String password) {

        ApplicationResponse checkPassword = accountApplicationService.checkPassword(account, password);
        if (!checkPassword.isSuccess()) {

            return RestfulResponses.of(checkPassword);
        }
        ApplicationResponse applicationResponse = applicationService.deposit(account, amount, description);
        if (applicationResponse.isSuccess()) {

            Account curAccount = (Account) applicationResponse.getBody();
            return RestfulResponses.of(applicationResponse, accountConverter.toDTO(curAccount));
        }
        return RestfulResponses.of(applicationResponse);
    }

    @PostMapping("/withdrawal")
    public RestfulResponse withdrawal(@RequestParam String account, @RequestParam Double amount,
                                   @RequestParam(required = false) String description, @RequestParam String password) {

        ApplicationResponse checkPassword = accountApplicationService.checkPassword(account, password);
        if (!checkPassword.isSuccess()) {

            return RestfulResponses.of(checkPassword);
        }
        ApplicationResponse applicationResponse = applicationService.withdrawal(account, amount, description);
        if (applicationResponse.isSuccess()) {

            Account curAccount = (Account) applicationResponse.getBody();
            return RestfulResponses.of(applicationResponse, accountConverter.toDTO(curAccount));
        }
        return RestfulResponses.of(applicationResponse);
    }

    @PostMapping("/transfer")
    public RestfulResponse transfer(@RequestParam String account, @RequestParam String targetAccount,
                                    @RequestParam Double amount, @RequestParam(required = false) String description,
                                    @RequestParam String password) {

        ApplicationResponse checkPassword = accountApplicationService.checkPassword(account, password);
        if (!checkPassword.isSuccess()) {

            return RestfulResponses.of(checkPassword);
        }
        ApplicationResponse applicationResponse = applicationService.transfer(account, targetAccount, amount, description);
        return RestfulResponses.of(applicationResponse);
    }

    @GetMapping("/findAll")
    public RestfulResponse findAll(@RequestParam String account,
                                   @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        ApplicationResponse applicationResponse = applicationService.findAll(account, pageNo, pageSize);
        Page<FundTransactionLog> page = (Page<FundTransactionLog>) applicationResponse.getBody();
        PageDTO<FundTransactionLogDTO> pageDTO = fundTransactionLogConverter.page(page);
        return RestfulResponses.of(applicationResponse, pageDTO);
    }
}