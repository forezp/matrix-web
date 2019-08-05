package io.github.forezp.common.util;



import io.github.forezp.permission.auth.RequestHolder;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.github.forezp.permission.auth.RequestHolder.REQUEST_SERVLET;

public class HttpUtils {


    /**
     * 尝试获取当前请求的HttpServletRequest实例
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        HttpServletRequest servletRequest = null;
        try {
            servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {

        }
        if (servletRequest == null) {
            servletRequest = (HttpServletRequest) RequestHolder.get().get(REQUEST_SERVLET);
        }
        return servletRequest;
    }


    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 解析参数列表为map
     *
     * @param request request请求对象
     * @return 参数集合
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Map<String, String> param = new LinkedHashMap<>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] varr = entry.getValue();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < varr.length; i++) {
                String var = varr[i];
                if (i != 0) {
                    builder.append(",");
                }
                builder.append(var);
            }
            param.put(key, builder.toString());
        }
        return param;
    }

    /**
     * 获取请求客户端的真实ip地址
     *
     * @param request 请求对象
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {

        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (int index = 0; index < ips.length; index++) {
                String strIp = (String) ips[index];
                if (!("unknown".equalsIgnoreCase(strIp))) {
                    ip = strIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 获取请求客户端的真实ip地址
     *
     * @param
     * @return ip地址
     */
    public static String getIpAddress() {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        return getIpAddress(getHttpServletRequest());
    }

    /**
     * web应用绝对路径
     *
     * @param request 请求对象
     * @return 绝对路径
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        return basePath;
    }




}
