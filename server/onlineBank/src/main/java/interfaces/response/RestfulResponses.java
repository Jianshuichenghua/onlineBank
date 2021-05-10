package interfaces.response;

import application.response.ApplicationResponse;

import javax.servlet.http.HttpServletResponse;

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
}