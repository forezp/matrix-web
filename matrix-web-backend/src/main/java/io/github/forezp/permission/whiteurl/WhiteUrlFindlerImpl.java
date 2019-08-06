//package io.github.forezp.permission.whiteurl;
//
//import io.github.forezp.permission.whiteurl.WhiteUrlFinder;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.regex.Pattern;
//
///**
// * Created by forezp on 2018/8/5.
// */
//@Component
//public class WhiteUrlFindlerImpl implements WhiteUrlFinder, InitializingBean {
//
//    private static final String COMMA = ",";
//
//    @Value("${permission.whiteurls:}")
//    String whiteurls;
//
//    List<String> list = new ArrayList<>();
//
//    @Override
//    public boolean isWhiteUrl(String url) {
//        if (list.contains(url)) {
//            return true;
//        }
//        for (int i = 0; i < list.size(); i++) {
//            String pattern = list.get(i);
//            boolean isMatch = Pattern.matches(pattern, url);
//            if (isMatch) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        //swagger相关的直接放行
//        list.add(".*webjars.*");
//        list.add(".*swagger.*");
//        list.add(".*api-docs.*");
//        list.add(".*druid.*");
//        if (StringUtils.isNotEmpty(whiteurls)) {
//            String[] whiteUrlArray = whiteurls.split(COMMA);
//            for (String url : whiteUrlArray) {
//                list.add(url);
//            }
//
//        }
//    }
//
//
//}
