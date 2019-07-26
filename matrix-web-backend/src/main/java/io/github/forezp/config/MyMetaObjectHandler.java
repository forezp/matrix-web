package io.github.forezp.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.github.forezp.common.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by forezp on 2018/8/3.
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {



    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("start insert fill ....");
        Object createBy = getFieldValByName("createBy", metaObject);
        Object updateBy = getFieldValByName("updateBy", metaObject);
        Object createTime = getFieldValByName("createTime", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);

        if (createBy == null) {
            this.setFieldValByName("createBy", UserUtils.getCurrentUserWithDefault(), metaObject);//版本号3.0.6以及之前的版本
        }
        if (updateBy == null) {
            this.setFieldValByName("updateBy", UserUtils.getCurrentUserWithDefault(), metaObject);
        }
        if (createTime == null) {
            this.setFieldValByName("createTime", new Date(), metaObject);
        }
        if (updateTime == null) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        Object updateBy = getFieldValByName("updateBy", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (updateBy == null) {
            this.setFieldValByName("updateBy", UserUtils.getCurrentUserWithDefault(), metaObject);
        }
        if (updateTime == null) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }

    }
}
