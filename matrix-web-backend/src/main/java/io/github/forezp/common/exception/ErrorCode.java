package io.github.forezp.common.exception;

/**
 * 通用错误码
 * Created by fangzhipeng on 2017/5/18.
 */
public enum ErrorCode {

    OK( 0, "" ),
    FAIL( -1, "操作失败" ),
    ERROR_ARGS( 1002, "参数不符合要求" ),
    USER_NOT_EXIST(1003, "该用户不存在"),
    PWD_ERROR(1004,"用户密码错误"),
    TOKEN_ISNULL(1005,"用户的token为空")
    ;



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
