//package com.weilai.common.interceptors;
//import cn.hutool.core.util.StrUtil;
//import com.weilai.common.utils.UserHolder;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
///**
// * 获取用户信息
// */
//public class UserInfoInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String userId = request.getHeader("userId");
//        if(StrUtil.isNotBlank(userId)){
//            // 保存用户id
//            UserHolder.saveUser(Long.parseLong(userId));
//        }
//        return true;
//    }
//
//
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // 清理用户信息
//        UserHolder.removeUser();
//    }
//}
