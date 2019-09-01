package io.github.forezp.common.exception;

/**
 * 通用错误码
 * Created by fangzhipeng on 2017/5/18.
 */
public enum ErrorCode {

    OK(0, ""),
    FAIL(-1, "操作失败"),
    NO_PERMISSION(401, "该用户没有权限"),
    ERROR_ARGS(1002, "参数不符合要求"),
    USER_NOT_EXIST(1003, "该用户不存在"),
    PWD_ERROR(1004, "用户密码错误"),
    TOKEN_ISNULL(1005, "用户的token为空"),
    INSERT_DATA_FAIL(1006, "插入数据失败"),
    INSERT_DATA_EXIST(1008, "插入的数据已经存在"),
    DELETE_DATA_FAIL(1007, "删除数据失败"),
    UPDATE_DATA_FAIL(1009, "更新数据失败"),
    ADD_TASK_FAIL(1010, "创建任务失败"),
    MODEL_NOT_EXIST(1011, "模型不存在"),
    FILE_NAME_NOT_EXIST(1013, "文件名称不存在"),
    FILE_PATTERN_NOT_SUPPORTED(1012, "文件格式不对"),
    MISSING_ARGS(1014, "参数缺失"),
    USER_IS_NOT_EXIST(1015,"用户不存在"),

    TASK_AUDIT_PARAM(5004, "流程判断节点参数错误");


    private int code;
    private String msg;


    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ErrorCode codeOf(int code) {
        for (ErrorCode state : values()) {
            if (state.getCode() == code) {
                return state;
            }
        }
        return null;
    }
}
