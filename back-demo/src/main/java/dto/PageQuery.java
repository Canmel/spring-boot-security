package dto;

import java.util.List;
import java.util.Map;

public class PageQuery {

    public List<BaseModel> result;

    public Map params;

    public Integer currentPage;

    public Integer pageSize;

    public Integer totalSize;

    public PageQuery() {
    }

    public PageQuery(List<BaseModel> result, Map params, Integer currentPage, Integer pageSize, Integer totalSize) {
        this.result = result;
        this.params = params;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
    }

    public List<BaseModel> getResult() {
        return result;
    }

    public Map getParams() {
        return params;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setResult(List<BaseModel> result) {
        this.result = result;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
}
