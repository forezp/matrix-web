package io.github.forezp.aop;


import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import io.github.forezp.common.dto.RespDTO;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.util.HttpUtils;
import io.github.forezp.common.util.JWTUtils;
import io.github.forezp.permission.auth.RequestHolder;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static io.github.forezp.permission.auth.RequestHolder.RESP_CODE;
import static io.github.forezp.permission.auth.RequestHolder.RESP_DTO;


/**
 * Created by fangzhipeng on 2017/8/25.
 */
@Component
@Aspect
public class ResutLogAspect {

    private Logger logger = LoggerFactory.getLogger(ResutLogAspect.class);

    @Pointcut("execution(public io.github.forezp.common.dto.RespDTO *(..))")
    public void loggerPointCut() {

    }

    @Around("loggerPointCut()")
    public RespDTO handleRespResult(ProceedingJoinPoint joinPoint) throws Exception {

        RespDTO respDTO = null;
        try {
            respDTO = (RespDTO) joinPoint.proceed();
        } catch (Throwable throwable) {
            if (throwable instanceof AriesException) {
                AriesException e = (AriesException) throwable;
                throw e;
            }

            if (throwable instanceof Exception) {
                Exception e = (Exception) throwable;
                throw e;
            }
        }
        if (respDTO != null) {
            logger.info("user:{} | result: {} ", getLogUser(), respDTO.toString());
            RequestHolder.get().putIfAbsent(RESP_DTO, JSON.toJSONString(respDTO));
            RequestHolder.get().putIfAbsent( RESP_CODE,respDTO.code);
        }
        return respDTO;

    }

    public  String getLogUser(){

        String token=null;
        try {
            token= HttpUtils.getHeaders(HttpUtils.getHttpServletRequest()).get("Authorization");

        }catch (Exception e){
            ExceptionUtils.printRootCauseStackTrace(e);
        }

        if(StringUtils.isEmpty(token)){
            return "noTokener";
        }
        try {
            Claims claims= JWTUtils.parseJWT(token);
            if(claims!=null){
                String userId=claims.getSubject();
                return userId;

            }
        } catch (Exception e) {
            ExceptionUtils.printRootCauseStackTrace(e);
        }
        return "noTokener";
    }

}
