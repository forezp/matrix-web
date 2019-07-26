package io.github.forezp.permission.auth;


import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by forezp on 2018/8/6.
 */
public class RequestHolder {

    public static final String REQUEST_SERVLET="requestServlet";

    public static final String REQUEST_ID="REQUEST_ID";

    public static final String START_TIME="startTime";

    public static final String RESP_DTO="respDto";

    public static final String RESP_CODE="respCode";

    public static final String CURRENT_TOKEN="currentToken";

    private static ThreadLocal<ConcurrentHashMap<String ,Object>> claimsThreadLocal = new ThreadLocal<ConcurrentHashMap<String ,Object>>(){

        @Override
        protected ConcurrentHashMap<String ,Object> initialValue() {
            return new ConcurrentHashMap<>();
        }
    };


    public static ConcurrentHashMap<String ,Object> get() {
        return claimsThreadLocal.get();
    }

    public static void remove() {
        claimsThreadLocal.remove();
    }


}
