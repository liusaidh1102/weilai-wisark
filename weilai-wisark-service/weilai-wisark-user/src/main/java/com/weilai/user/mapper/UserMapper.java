package com.weilai.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.user.dos.User;
import com.weilai.model.user.vos.UserVO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {


    /**
     * 获取用户信息
     * @param userId 用户id
     * @return 返回vo
     */
    UserVO selectUserVO(@Param("userId") Long userId);


}
