package io.github.forezp.config;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by forezp on 2018/8/3.
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    Logger logger= LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info("start insert fill ....");
        this.setFieldValByName("createBy", "Forezp", metaObject);//版本号3.0.6以及之前的版本
        this.setFieldValByName("createTime", new Date(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("updateBy", "forezp", metaObject);
        this.setFieldValByName("updateTime", new Date(System.currentTimeMillis()), metaObject);
        //this.setInsertFieldValByName("operator", "Jerry", metaObject);//@since 快照：3.0.7.2-SNAPSHOT， @since 正式版暂未发布3.0.7
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("start update fill ....");
        this.setFieldValByName("updateBy", "forezp", metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);

    }
}
