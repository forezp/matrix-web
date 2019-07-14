package io.github.forezp.common.util;


import io.github.forezp.common.exception.AriesException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.forezp.common.exception.ErrorCode;

/**
 * @author: fangzhipeng.
 * @createTime: 2017/8/4.
 */
public class PageUtils {

    private static Logger logger = LoggerFactory.getLogger(PageUtils.class);

    public static void check(int page, int pageSize) {
        if (!(page > 0) || !(pageSize > 0)) {
            logger.error("分页参数错误 page={} , pageSize={}", page, pageSize); //by cjn 2017/08/09
            throw new AriesException(ErrorCode.ERROR_ARGS, "分页参数错误");
        }
    }
}
