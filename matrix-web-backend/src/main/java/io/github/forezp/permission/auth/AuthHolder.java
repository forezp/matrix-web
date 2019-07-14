package io.github.forezp.permission.auth;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by forezp on 2018/8/6.
 */
public class AuthHolder {

    private static ThreadLocal<HttpServletRequest> claimsThreadLocal = new ThreadLocal<>();

    public static void set(HttpServletRequest httpServletRequest) {
        claimsThreadLocal.set(httpServletRequest);
    }

    public static HttpServletRequest get() {
        return claimsThreadLocal.get();
    }

    public static void remove() {
        claimsThreadLocal.remove();
    }
}
