package io.github.forezp.modules.activiti.vo.dto;

import java.util.List;

/**
 * @author: xufei.
 * @createTime: 2017/9/11.
 */
public class TaskListDTO {
    private String category;
    private Integer page;
    private Integer pageSize;
    private Integer totalCount;
    private List<TaskDTO> tasks;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
