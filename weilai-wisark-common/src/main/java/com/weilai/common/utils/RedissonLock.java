package com.weilai.common.utils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;
@Component
public class RedissonLock implements ILock{


    /**
     * 业务或资源名称
     */
    private final String service;

    private final RedissonClient redissonClient;


    public RedissonLock(String service,RedissonClient redissonClient) {
        this.service = service;
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean tryLock(long time,TimeUnit timeUnit) throws InterruptedException {
        RLock lock = redissonClient.getLock(service);
        return lock.tryLock(time, timeUnit);
    }

    @Override
    public void unlock() {
        RLock lock = redissonClient.getLock(service);
        lock.unlock();
    }
}