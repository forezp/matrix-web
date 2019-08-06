package io.github.forezp.common.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by fangzhipeng on 2017/5/24.
 */
@Component
public class RedisTemplater {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value);
    }

    /**
     * 模糊搜索，获取某一类的keys
     * @param patternStr
     * @return
     */
    public Set<String> getPatternKeys(String patternStr){
       return stringRedisTemplate.keys("*" + patternStr + "*");
    }
    public void setKey(String key, String value, int minute) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value, minute, TimeUnit.MINUTES);
    }

    public void deleteKey(String key) {
        stringRedisTemplate.delete(key);
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        return ops.get(key);
    }

    public boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public Long addAndGetIncValue(String key) {
        RedisAtomicLong counter = new RedisAtomicLong(key, stringRedisTemplate.getConnectionFactory());
        return counter.addAndGet(1L);
    }

    public Long getIncrValue(String key) {
        RedisAtomicLong counter = new RedisAtomicLong(key, stringRedisTemplate.getConnectionFactory());
        return counter.get();
    }
}
