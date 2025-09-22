package com.weilai.model.user.vos;
import lombok.Data;
import java.util.List;
/**
 * 返回用户权限信息 + token
 */
@Data
public class MenuItem {

    private Long id;

    private String key;

    private String label;

    private String icon;

    private Integer sort;

    private Long parentId;

    private List<MenuItem> children;

}
