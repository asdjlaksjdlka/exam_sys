package com.qf.examsys.dao;

import com.qf.examsys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {

    //查询所有角色
    List<Role> findAllRoles();

    //插入角色，返回rid
    void insertRole(Role role);

    //修改user与role的中间表
    void updateUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);

    //插入权限
    void insertIntoRolePerm(@Param("rid")Integer rid,@Param("pid") Integer pid);

    Role findRoleById(Integer rid);

    void updateRoleName(Role role);

    void deleteRolePerm(Integer rid);

}
