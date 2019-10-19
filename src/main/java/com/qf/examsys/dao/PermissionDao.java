package com.qf.examsys.dao;

import com.qf.examsys.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionDao {

    List<Permission> findAllFirMenusByUid(Integer uid);

    List<Permission> findAllSecMenus(@Param("uid") Integer uid ,@Param("pareName") String pareName);

    List<Permission> findAllPermission();

    List<Permission> findAllChildPermission(Integer pid);

    List<Permission> fidnAllPermissionByUid(Integer uid);

    void updateUserRole(Integer uid);

    List<Permission> findRolePermsByRid(Integer rid);


}
