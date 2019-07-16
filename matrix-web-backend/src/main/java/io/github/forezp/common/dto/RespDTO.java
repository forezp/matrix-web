package io.github.forezp.common.dto;

import io.github.forezp.permission.auth.RequestHolder;

import java.io.Serializable;

import static io.github.forezp.permission.auth.RequestHolder.REQUEST_ID;


public class RespDTO<T> implements Serializable {


    public int code = 0;
    public String message = "";
    public T data;
    public String requestId;

    public static RespDTO onSuc(Object data) {
        RespDTO resp = new RespDTO();
        if (RequestHolder.get() != null) {
            if (RequestHolder.get().get(REQUEST_ID) != null) {
                resp.requestId = RequestHolder.get().get(REQUEST_ID).toString();
            }
        }
        resp.data = data;
        return resp;
    }

    @Override
    public String toString() {
        return "RespDTO{" +
                "code=" + code +
                ", error='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
