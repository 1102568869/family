package tech.washmore.family.model;

import java.util.List;

public class Page<T> {
    private List<T> list;
    private int totalCount;

    private int pageSize;
    private int pageNo;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return (int) Math.ceil(totalCount * 1.0 / pageSize);
    }

    public boolean isFirstPage() {
        return getPageNo() == 1;
    }

    public boolean isLastPage() {
        return getPageNo() == getTotalPage();
    }

    public int getPrevPageNo() {
        return isFirstPage() ? 1 : getPageNo() - 1;
    }

    public int getNextPageNo() {
        return isLastPage() ? getTotalPage() : getPageNo() + 1;
    }

}
