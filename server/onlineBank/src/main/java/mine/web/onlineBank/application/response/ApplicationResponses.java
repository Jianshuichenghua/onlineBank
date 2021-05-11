package mine.web.onlineBank.application.response;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
public final class ApplicationResponses {

    public static final ApplicationResponse success(Object body) {

        ApplicationResponse response = new ApplicationResponse(true, ApplicationResponseCodes.SUCCESS);
        response.setBody(body);
        return response;
    }

    public static final ApplicationResponse success() {

        ApplicationResponse response = new ApplicationResponse(true, ApplicationResponseCodes.SUCCESS);
        return response;
    }

    public static final ApplicationResponse fail(String code, Object body) {

        ApplicationResponse response = new ApplicationResponse(false, code);
        response.setBody(body);
        return response;
    }

    public static final ApplicationResponse fail(String code) {

        ApplicationResponse response = new ApplicationResponse(false, code);
        return response;
    }
}