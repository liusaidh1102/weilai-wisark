package com.weilai.common.utils;

import java.util.concurrent.TimeUnit;

public interface ILock {


    /**
     * 尝试获取锁
     * @param service 业务或资源名称
     * @param time 锁超时时间
     * @param unit  超时时间
     * @return true:获取锁成功
     */
    boolean tryLock(String  service, long time, TimeUnit  unit) throws InterruptedException;


    /**
     * 释放锁
     * @param service 业务或资源名称
     */
    void unlock(String service);


}
