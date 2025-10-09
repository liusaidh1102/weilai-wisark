package com.weilai.common.utils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import static com.weilai.common.constants.CacheConstant.LOCK_KEY_PREFIX;
// 基于redis的setnx命令实现分布式锁
//@Component
public class RedisSimpleLock implements ILock {

    /**
     * 业务或资源名称
     */

    private final StringRedisTemplate stringRedisTemplate;

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;

    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    public RedisSimpleLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // 每个jvm实例仅有一份，确保value(ID_PREFIX + ThreadId)重复的概率降低
    private static final String ID_PREFIX = UUID.randomUUID().toString() + "_";


    @Override
    public boolean tryLock(String service,long time,TimeUnit timeUnit) {
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        // 当前锁的key   lock + 资源名
        String lockKey = LOCK_KEY_PREFIX + service;
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, threadId,time, timeUnit);
        return Boolean.TRUE.equals(flag);
    }

    @Override
    public void unlock(String  service) {
        String threadId = ID_PREFIX + Thread.currentThread().getId();
//        String threadIdInRedis = stringRedisTemplate.opsForValue().get(LOCK_KEY_PREFIX + service);
//        if (threadId.equals(threadIdInRedis)){
//            stringRedisTemplate.delete(LOCK_KEY_PREFIX + service);
//        }
        // 确保操作的原子性
        stringRedisTemplate.execute(UNLOCK_SCRIPT, Collections.singletonList(LOCK_KEY_PREFIX + service), threadId);
    }
}