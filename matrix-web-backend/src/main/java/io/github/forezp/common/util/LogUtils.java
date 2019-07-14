package io.github.forezp.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by forezp on 2019/7/14.
 */
public class LogUtils {


    private Logger logger;
    public  LogUtils (Class clz){
        logger= LoggerFactory.getLogger(clz);
    }

    public void info(String msg){
        logger.info("");
    }
}
