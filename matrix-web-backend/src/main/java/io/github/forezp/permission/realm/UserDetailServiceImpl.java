package io.github.forezp.permission.realm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired

    @Override
    public UserDetail getUserDetail(String userId) {
        return null;
    }
}
