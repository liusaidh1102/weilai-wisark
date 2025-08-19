package com.weilai.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.user.dos.UserThirdAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountMapper extends BaseMapper<UserThirdAccount> {
}
