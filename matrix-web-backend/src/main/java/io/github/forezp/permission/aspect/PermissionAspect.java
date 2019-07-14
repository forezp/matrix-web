//package io.github.forezp.permission.aspect;
//
//
//import io.github.forezp.common.constant.OrderConstants;
//import io.github.forezp.common.util.HttpUtils;
//import io.github.forezp.datasources.DataSourceNames;
//import io.github.forezp.datasources.DynamicDataSource;
//import io.github.forezp.datasources.annotation.DataSource;
//import io.github.forezp.modules.system.service.SysPermissionService;
//import io.github.forezp.permission.HasPermission;
//import io.github.forezp.permission.whiteurl.WhiteUrlFinder;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//
//
//@Aspect
//@Component
//public class PermissionAspect implements Ordered {
//
//    protected Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    SysPermissionService sysPermissionService;
//
//    @Autowired
//    WhiteUrlFinder whiteUrlFinder;
//
//    @Pointcut("@annotation(io.github.forezp.permission.HasPermission)")
//    public void permissionPointCut() {
//
//    }
//
//    @Around("permissionPointCut()")
//    public Object before(ProceedingJoinPoint point) throws Throwable {
//        //去掉白名单
//        String uri = HttpUtils.getHttpServletRequest().getRequestURI();
//        if (whiteUrlFinder.isWhiteUrl(uri)) {
//            return point.proceed();
//        }
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = signature.getMethod();
//
//        Annotation[] methodAnnotations = method.getDeclaredAnnotations();
//        for (Annotation annotation : methodAnnotations) {
//            if (annotation instanceof HasPermission) {
//
//
//            }
//        }
//
//        return point.proceed();
//    }
//
//
//    @Override
//    public int getOrder() {
//        return OrderConstants.PERMISSION_ORDOR;
//    }
//}
