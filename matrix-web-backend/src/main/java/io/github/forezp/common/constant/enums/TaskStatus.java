package io.github.forezp.common.constant.enums;

public enum TaskStatus {

    CREATED("已创建"),
    PAUSED("已暂停"),
    RESUME("已恢复"),
    UPDATED("已更新"),
    DELETED("已删除");

    private String status;

    TaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
