package com.qf.examsys.dao;

import com.qf.examsys.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {

    //用户登录时，根据权限动态显示页面，返会 id，name，url
    List<Menu> findAllFirstMenus(Integer uid);

    //根据用户id和父id查询二级菜单
    List<Menu> findAllSecMenus(@Param("uid") Integer uid,@Param("parentId") Integer parentId);

    //通过用户id只返回权限 name
    List<String> findAllFirstPerms(Integer uid);

    //根据用户id和权限父id返回二级菜单
    List<String> findAllSecondPerms(@Param("uid") Integer uid,@Param("parentId") Integer parentId);

    //返回所用权限的名字
    List<String> findAllPerm();

    //二级权限名
    List<String> findPermsByName(String pname);
}
