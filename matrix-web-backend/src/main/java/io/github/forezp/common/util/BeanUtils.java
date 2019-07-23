package io.github.forezp.common.util;

import com.google.common.collect.Lists;


import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Collection;
import java.util.List;

/**
 * Created by forezp on 2019/7/21.
 */
@Component
public class BeanUtils {

    private BeanUtils() {

    }


    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return Application.getBean(DozerBeanMapper.class).map(source, destinationClass);
    }



    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = Application.getBean(DozerBeanMapper.class).map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }


    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        Application.getBean(DozerBeanMapper.class).map(source, destinationObject);
    }

//
//    public static void main(String[] args) {
//        SysOrg org=new SysOrg();
//        org.setId(1l);
//        org.setSimpleName("sss");
//        org.setFullName("ddddeee");
//
//        SysOrgDTO sysOrgDTO=BeanUtils.map(org, SysOrgDTO.class);
//       System.out.print(sysOrgDTO.toString());
//    }

}
