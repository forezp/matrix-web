package io.github.forezp.common.util;



import io.github.forezp.common.dto.ValidationResult;
import io.github.forezp.common.exception.AriesException;
import io.github.forezp.common.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用户参数传入校验，（长度等）
 * Created by fangzhipeng on 2017/9/7.
 */
public class ValidatorUtils {


    private static Logger logger = LoggerFactory.getLogger(ValidatorUtils.class);// by cc 2017/09/11 参数校验日志输入用

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 检验实体
     * @param obj
     * @param <T>
     */
    public static <T> void validateEntity(T obj) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() > 0) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        checkError(result);
    }

    private static void checkError(ValidationResult result) {
        if (result.isHasErrors()) {
            logger.error("校验实体有错误,错误为{}", result.toString());
            if (result.getErrorMsg().size() > 0) {

                throw new AriesException(ErrorCode.ERROR_ARGS, mapValueToString(result.getErrorMsg()));
            }
            throw new AriesException(ErrorCode.ERROR_ARGS);
        }
    }
    private static String mapValueToString(Map<String, String> errorMsg) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, String>> set = errorMsg.entrySet();
        for (Map.Entry<String, String> entry : set) {
            sb.append(entry.getValue());
            sb.append(",");
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 检验实体中的某个字段
     *
     * @param obj
     * @param propertyName
     * @param <T>
     */
    public static <T> void validateProperty(T obj, String propertyName) {
        logger.debug("校验参数 {}", propertyName);
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (set != null && set.size() > 0) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        checkError(result);
    }
}
