package io.githib.forezp.vue.util;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;
import com.nepxion.skeleton.engine.property.SkeletonProperties;

import java.io.IOException;

public class SkeletonPropertiesUtils {

    public static SkeletonProperties buildSkeletonProperties(String propertiesPath) {
        SkeletonProperties skeletonProperties = null;
        try {
            skeletonProperties = new SkeletonProperties(propertiesPath, SkeletonConstant.ENCODING_GBK, SkeletonConstant.ENCODING_UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return skeletonProperties;
    }


}
