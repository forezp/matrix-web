package io.github.forezp.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * Created by forezp on 2018/8/3.
 */
public class MyMetaObjectHandler extends MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
//
//        System.out.println("*************************");
//        System.out.println("insert fill");
//        System.out.println("*************************");
//
//        // 测试下划线
//        Object testType = getFieldValByName("createBy", metaObject);//mybatis-plus版本2.0.9+
//        System.out.println("createBy=" + testType);
//        if (testType == null) {
//            setFieldValByName("createBy", "forezp", metaObject);//mybatis-plus版本2.0.9+
//        }
//
//        setFieldValByName("createTime", new Date(), metaObject);
//        setFieldValByName("updateTime", new Date(), metaObject);
//        setFieldValByName("updateBy", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

//        setFieldValByName("updateTime", new Date(), metaObject);
//        setFieldValByName("updateBy", "forezp", metaObject);

    }
}
