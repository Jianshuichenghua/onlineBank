package application.response;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
public class ApplicationResponse {

    private boolean success;

    private String code;

    private Object body;

    public ApplicationResponse(boolean success, String code) {
        this.success = success;
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}