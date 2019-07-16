package io.github.forezp.common.util;


import io.github.forezp.permission.auth.RequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.forezp.permission.auth.RequestHolder.REQUEST_ID;

/**
 * Created by forezp on 2019/7/14.
 */
public class LogUtils {


    private Logger logger;

    public LogUtils(Class clz) {
        logger = LoggerFactory.getLogger(clz);
    }

    public void info(String msg) {
        Object requestId = RequestHolder.get().get(REQUEST_ID);
        if (requestId != null) {
            logger.info(requestId.toString() + " " + msg);
        } else {
            logger.info(msg);
        }
    }


    public void info(String format, Object... arguments) {
        Object requestId = RequestHolder.get().get(REQUEST_ID);
        if (requestId != null) {
            logger.info(requestId.toString() + " " + format, arguments);
        } else {
            logger.info(format, arguments);
        }
    }
}
