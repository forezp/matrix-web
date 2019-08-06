package io.github.forezp.common.cache.caffine;


import com.github.benmanes.caffeine.cache.LoadingCache;



public abstract class AbstractCaffineCache<T> {

    protected LoadingCache<String, T> loadingCache;


    abstract LoadingCache<String, T>  createLoadingCache();

    public boolean put(String key, T value) {
        if(loadingCache==null){
            loadingCache=createLoadingCache();
        }
        loadingCache.put(key, value);

        return Boolean.TRUE;
    }

    public T get(String key) {
        if(loadingCache==null){
            loadingCache=createLoadingCache();
        }
        try {

            return loadingCache.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean clear(String key) {
        if(loadingCache==null){
            loadingCache=createLoadingCache();
        }
        loadingCache.invalidate(key);
        return Boolean.TRUE;
    }

}
