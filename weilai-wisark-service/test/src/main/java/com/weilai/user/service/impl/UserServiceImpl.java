package com.weilai.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.user.mapper.UserMapper;
import com.weilai.user.service.UserService;
import com.weilai.model.user.dos.User;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}