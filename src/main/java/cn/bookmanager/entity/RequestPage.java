package cn.bookmanager.entity;

/**
 * 分页所需参数
 * @author lengqie
 */
public class RequestPage {
    private int page;
    private int size;

    /**
     * 页码，为非必传参数，默认值为 1
     */

    public Integer getPage() {
        return this.page <=0  ? 1 : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 大小，非必传参数，默认值为 10
     */
    public Integer getSize() {
        return this.size <=0 ? 10 : size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
