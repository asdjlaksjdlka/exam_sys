package com.qf.examsys.service;

import com.qf.examsys.entity.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    List<Map<String,Object>> findMenus(Integer uid);

    List<Permission> findAll();

    List<Permission> findAllByUid(Integer uid);


    void updateUserPerms(Integer uid,String ids);

    List<Permission> findRolePermByRid(Integer rid);

    void updateRolePerms(Integer rid,String str);
}
