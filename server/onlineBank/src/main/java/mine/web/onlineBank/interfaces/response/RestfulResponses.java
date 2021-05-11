package mine.web.onlineBank.interfaces.response;

import mine.web.onlineBank.application.response.ApplicationResponse;

import javax.servlet.http.HttpServletResponse;
import mine.web.onlineBank.interfaces.heartbeat.HeartbeatReponseCodes;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
public final class RestfulResponses {

    public static final RestfulResponse of(ApplicationResponse applicationResponse, Object body) {

        RestfulResponse response = applicationResponse.isSuccess() ? new RestfulResponse(HttpServletResponse.SC_OK, applicationResponse.getCode())
                : new RestfulResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, applicationResponse.getCode());
        response.setBody(body);
        return response;
    }

    public static final RestfulResponse of(ApplicationResponse applicationResponse) {

        RestfulResponse response = applicationResponse.isSuccess() ? new RestfulResponse(HttpServletResponse.SC_OK, applicationResponse.getCode())
                : new RestfulResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, applicationResponse.getCode());
        response.setBody(applicationResponse.getBody());
        return response;
    }

    public static final RestfulResponse heartbeat() {

        return new RestfulResponse(HttpServletResponse.SC_OK, HeartbeatReponseCodes.HEARTBEAT);
    }
}