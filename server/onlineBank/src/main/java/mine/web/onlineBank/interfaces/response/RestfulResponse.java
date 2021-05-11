package mine.web.onlineBank.interfaces.response;

import javax.servlet.http.HttpServletResponse;

/**
 * @date 2021/5/10
 * @author xujunfeng
 * @description
 */
public class RestfulResponse {

    private int status;

    private String code;

    private Object body;

    public RestfulResponse(int status, String code) {
        this.status = status;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public boolean isSuccessful() {

        return HttpServletResponse.SC_OK == status;
    }
}