package io.githib.forezp.vue;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 *
 * @author Haojun Ren
 * @version 1.0
 */

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.engine.util.SkeletonUtil;
import com.nepxion.skeleton.engine.util.StringUtil;
import com.nepxion.skeleton.framework.service.SkeletonService;
import io.githib.forezp.vue.db.MysqlTbFieldReader;
import io.githib.forezp.vue.db.TableFieldReader;
import io.githib.forezp.vue.entity.TableField;
import io.githib.forezp.vue.impl.VueProImpl;
import io.githib.forezp.vue.util.MyClassLoader;
import io.githib.forezp.vue.util.SkeletonPropertiesUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class App {

    // 创建文件的输出的路径
    private static final String generatePath = "/Users/nickelfang/work/github/github202007";
    // 放在操作系统的临时目录下
    //String generatePath = SkeletonUtil.getTempGeneratePath();
    // 模板文件所在的前置路径
    private static final String prefixTemplatePath = "vue/template";
    // 模板路径缩减
    private static final String reducedTemplatePath = "io/github/forezp/vue/generator/";

    // 配置文件含中文，stringEncoding必须为GBK，readerEncoding必须为UTF-8，文本文件编码必须为ANSI
    private static final String propertiesPath = "vue/config/skeleton-data.properties";
    private static final String tableName = "user";

    public static void main(String[] args) throws Exception {
        genVueCode(tableName, generatePath, prefixTemplatePath, reducedTemplatePath, propertiesPath);
    }

    public static void genVueCode(String tableName, String generatePath, String prefixTemplatePath,
                                  String reducedTemplatePath, String propertiesPath) throws Exception {

        // 全局上下文对象
        SkeletonContext skeletonContext = new SkeletonContext(generatePath, prefixTemplatePath, reducedTemplatePath);

        // 全局配置类对象
        SkeletonProperties skeletonProperties = new SkeletonProperties(propertiesPath, SkeletonConstant.ENCODING_GBK,
                SkeletonConstant.ENCODING_UTF_8);

        TableFieldReader tblFieldReader = new MysqlTbFieldReader();
        List<TableField> tableFields = tblFieldReader.read(tableName);
        Map<String, Object> map = skeletonProperties.getMap();
        map.put("className", tableName);
        List<String> values = new ArrayList<>();
        for (TableField tableField : tableFields) {
            String field = tableField.getField();
            if (StrUtil.isNotBlank(field)) {
                values.add(field);
            }
        }
        map.put("values", values);
        map.put("apiName", tableName);
        // 输出脚手架文件
        SkeletonService skeletonService = new VueProImpl();
        skeletonService.generate(skeletonContext, skeletonProperties);
    }


}