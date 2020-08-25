package io.githib.forezp.vue.impl;

import com.nepxion.skeleton.engine.context.SkeletonContext;
import com.nepxion.skeleton.engine.exception.SkeletonException;
import com.nepxion.skeleton.engine.property.SkeletonProperties;
import com.nepxion.skeleton.framework.service.SkeletonService;
import freemarker.template.TemplateException;
import io.githib.forezp.vue.generator.ApiGenerator;
import io.githib.forezp.vue.generator.IndexJsGenerator;
import io.githib.forezp.vue.generator.IndexVueGenerator;


import java.io.IOException;


public class VueProImpl implements SkeletonService {

    @Override
    public void generate(SkeletonContext skeletonContext, SkeletonProperties skeletonProperties) throws SkeletonException, TemplateException, IOException {
        new ApiGenerator(skeletonContext, skeletonProperties).generate();
        new IndexVueGenerator(skeletonContext, skeletonProperties).generate();
        new IndexJsGenerator(skeletonContext, skeletonProperties).generate();
    }
}
