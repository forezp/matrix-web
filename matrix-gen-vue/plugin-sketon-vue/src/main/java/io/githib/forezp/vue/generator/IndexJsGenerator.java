package io.githib.forezp.vue.generator;

import cn.hutool.core.util.StrUtil;
import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.generator.SkeletonFileGenerator;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class IndexJsGenerator extends SkeletonFileGenerator {
    Logger logger = LoggerFactory.getLogger(SkeletonFileGenerator.class);

    public IndexJsGenerator(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) {
        super(skeletonContext.clone(null, ApiGenerator.class), skeletonProperties);
    }

    @Override
    protected String getFileName() {
        String clzName = skeletonProperties.getString("className");
        if (StrUtil.isNotBlank(clzName)) {
            return clzName + ".js";
        } else {
            return "index.js";
        }

    }

    @Override
    protected String getTemplateName() {
        return "index.js.template";
    }

    @Override
    protected Object getDataModel() {

        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("pomGroupId", skeletonProperties.getString("pomGroupId"));
        dataModel.put("pomArtifactId", skeletonProperties.getString("pomArtifactId"));
        dataModel.put("pomName", skeletonProperties.getString("pomName"));
        dataModel.put("pomVersion", skeletonProperties.getString("pomVersion"));
        dataModel.put("springCloudVersion", skeletonProperties.getString("springCloudVersion"));
        dataModel.put("springBootVersion", skeletonProperties.getString("springBootVersion"));
        dataModel.put("javaVersion", skeletonProperties.getString("javaVersion"));
        dataModel.put("apiName", skeletonProperties.getString("apiName"));
        logger.info("apiName:" + skeletonProperties.getString("apiName"));
        dataModel.putAll(skeletonProperties.getMap());
        return dataModel;
    }
}