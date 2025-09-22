package com.weilai.model.user.vos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {

    /**
     * token
     */
    private String token;

    /**
     * 菜单
     */
    private List<MenuItem> menuItem;


    /**
     * 权限
     */
    private List<String> buttomList;


    /**
     * 用户基本信息
     */
    private UserVO userVO;

}