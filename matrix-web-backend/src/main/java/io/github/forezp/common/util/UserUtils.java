package io.github.forezp.common.util;


import io.github.forezp.permission.auth.RequestHolder;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static io.github.forezp.common.constant.CommonConstants.STRANGER;
import static io.github.forezp.permission.auth.RequestHolder.CURRENT_TOKEN;

/**
 * Created by forezp on 2019/7/26.
 */
public class UserUtils {

    public static String getCurrentToken() {
        String token = (String) RequestHolder.get().get(CURRENT_TOKEN);
        return token;
    }

    public static String getCurrentUser() {

        String token = getCurrentToken();
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        try {
            Claims claims = JWTUtils.parseJWT(token);
            if (claims != null) {
                String userId = claims.getSubject();
                return userId;

            }
        } catch (Exception e) {
            ExceptionUtils.printRootCauseStackTrace(e);
        }
        return null;
    }

    public static String getCurrentUserWithDefault() {
        String currentUser = getCurrentUser();
        return currentUser == null ? STRANGER : currentUser;
    }

}
