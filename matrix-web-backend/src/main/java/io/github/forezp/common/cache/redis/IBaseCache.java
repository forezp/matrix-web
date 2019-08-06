package io.github.forezp.common.cache.redis;

import java.util.List;

/**
 * Created by forezp on 2017/10/18.
 */
public interface IBaseCache<T> {

    void setById(String id, T o);

    void setById(String id, T o, int timeout);

    void set(String key, T o);

    void set(String key, T o, int timeout);

    T getById(String id);

    T get(String key);

    void setList(List<T> list);

    void setList(List<T> list, int timeout);

    boolean hasList();

    List<T> getList();

    void setList(String key, List<T> list);

    void setList(String key, List<T> list, int timeout);

    boolean hasList(String key);

    List<T> getList(String key);

    boolean hasKeyById(String id);

    boolean hasKey(String key);

    void deleteKeyById(String id);

    void deleteKey(String key);

    void deleteList();

    void deleteList(String key);

    void deleteAllEntityKeys();


}
