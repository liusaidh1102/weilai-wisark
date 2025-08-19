package com.wisark.api.feign;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户相关远程调用
 */
@FeignClient("weilai-wisark-user")
public interface UserClient {



}
