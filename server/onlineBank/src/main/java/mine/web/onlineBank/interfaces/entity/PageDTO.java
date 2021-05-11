package mine.web.onlineBank.interfaces.entity;

import java.util.List;

/**
 * @date 2021/5/11
 * @author xujunfeng
 * @description
 */
public class PageDTO<T extends IdEntityDTO> {

    private Long totalCount;

    private List<T> result;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}