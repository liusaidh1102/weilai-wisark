package com.weilai.common.utils;

import java.util.concurrent.TimeUnit;

public interface ILock {


    /**
     * 尝试获取锁
     * @param time 锁超时时间
     * @param unit  超时时间
     * @return true:获取锁成功
     */
    boolean tryLock(long time, TimeUnit  unit) throws InterruptedException;


    /**
     * 释放锁
     */
    void unlock();


}
