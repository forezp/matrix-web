package io.github.forezp.permission.realm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserPermissonService userPermissonService;

    @Override
    public UserDetail getUserDetail(String userId) {
        return userPermissonService.getUserRolePerssion(userId);
    }
}
