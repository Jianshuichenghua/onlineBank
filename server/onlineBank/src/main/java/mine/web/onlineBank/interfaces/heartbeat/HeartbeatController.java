package mine.web.onlineBank.interfaces.heartbeat;

import mine.web.onlineBank.interfaces.response.RestfulResponse;
import mine.web.onlineBank.interfaces.response.RestfulResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2021/5/11
 * @author xujunfeng
 * @description
 */
@RequestMapping("/heartbeat")
@RestController
public class HeartbeatController {

    @GetMapping
    public RestfulResponse heartbeat() {

        return RestfulResponses.heartbeat();
    }
}