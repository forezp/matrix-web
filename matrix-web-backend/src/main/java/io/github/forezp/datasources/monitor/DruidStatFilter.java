//package io.github.forezp.datasources.monitor;
//
//import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
//
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//
///**
// * Created by forezp on 2018/8/10.
// */
//@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
//        initParams={@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
//        }
//)
//public class DruidStatFilter extends DruidStatProperties.WebStatFilter {
//
//}