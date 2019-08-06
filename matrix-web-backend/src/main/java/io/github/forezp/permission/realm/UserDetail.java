package io.github.forezp.permission.realm;

import java.util.List;

public interface UserDetail {


    List<String> getRoles();

    List<String> getPermissions();
}
