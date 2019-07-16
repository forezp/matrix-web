package io.github.forezp.permission.auth;


import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by forezp on 2018/8/6.
 */
public class RequestHolder {

    public static final String REQUEST_SERVLET="requestServlet";

    public static final String REQUEST_ID="requestId";

    public static final String START_TIME="startTime";

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
