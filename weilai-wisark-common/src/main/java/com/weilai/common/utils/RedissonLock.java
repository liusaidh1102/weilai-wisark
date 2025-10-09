package com.weilai.common.utils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
import static com.weilai.common.constants.CacheConstant.LOCK_KEY_PREFIX;
@Component
public class RedissonLock implements ILock{

    private final RedissonClient redissonClient;


    public RedissonLock(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean tryLock(String service,long time,TimeUnit timeUnit) throws InterruptedException {
        RLock lock = redissonClient.getLock(LOCK_KEY_PREFIX + service);
        return lock.tryLock(time, timeUnit);
    }

    @Override
    public void unlock(String service) {
        RLock lock = redissonClient.getLock(LOCK_KEY_PREFIX + service);
        lock.unlock();
    }
}