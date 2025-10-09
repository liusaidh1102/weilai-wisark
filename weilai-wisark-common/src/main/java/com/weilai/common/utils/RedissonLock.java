package com.weilai.common.utils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
import static com.weilai.common.constants.CacheConstant.LOCK_KEY_PREFIX;
@Component
public class RedissonLock implements ILock{

    private final RedissonClient redissonClient;

    private final String service;

    public RedissonLock(RedissonClient redissonClient, String service) {
        this.redissonClient = redissonClient;
        this.service = service;
    }

    @Override
    public boolean tryLock(long timeoutSec) throws InterruptedException {
        RLock lock = redissonClient.getLock(LOCK_KEY_PREFIX + service);
        return lock.tryLock(timeoutSec, TimeUnit.SECONDS);
    }

    @Override
    public void unlock() {
        RLock lock = redissonClient.getLock(LOCK_KEY_PREFIX + service);
        lock.unlock();
    }
}