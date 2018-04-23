package dto;

import org.springframework.util.ObjectUtils;

import javax.persistence.Transient;
import java.util.Date;

public class BaseModel {

    @Transient
    public Integer currentPage;

    @Transient
    public Integer pageSize;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BaseModel(Integer currentPage, Integer pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public BaseModel() {
    }

    public Integer getCurrentPage() {
        if(ObjectUtils.isEmpty(currentPage)){
            currentPage = 1;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        if(ObjectUtils.isEmpty(pageSize)){
            pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
