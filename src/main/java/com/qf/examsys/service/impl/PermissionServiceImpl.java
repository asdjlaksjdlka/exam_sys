package com.qf.examsys.service.impl;

import com.qf.examsys.dao.PermissionDao;
import com.qf.examsys.dao.RoleDao;
import com.qf.examsys.dao.UserDao;
import com.qf.examsys.entity.Permission;
import com.qf.examsys.entity.Role;
import com.qf.examsys.entity.User;
import com.qf.examsys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired(required = false)
    PermissionDao permissionDao;

    @Autowired(required = false)
    RoleDao roleDao;

    @Autowired(required = false)
    UserDao userDao;


    /*
    * 菜单
    * */
    @Override
    public List<Map<String,Object>> findMenus(Integer uid) {

        List<Map<String, Object>> list = new ArrayList<>();
        List<Permission> permissions = permissionDao.findAllFirMenusByUid(uid);
        for (Permission p:permissions){

            HashMap<String, Object> hashMap = new HashMap<>();
            List<Permission> secMenus = permissionDao.findAllSecMenus(uid, p.getName());
            hashMap.put("parent",p);
            hashMap.put("children",secMenus);
            list.add(hashMap);
        }
        return list;
    }

    @Override
    public List<Permission> findAll() {
        List<Permission> list = permissionDao.findAllPermission();
        for (Permission p :list){
            List<Permission> children = permissionDao.findAllChildPermission(p.getId());
            p.setChildren(children);
        }
        return list;
    }

    @Override
    public List<Permission> findAllByUid(Integer uid) {
        List<Permission> list = permissionDao.fidnAllPermissionByUid(uid);
        return list;
    }

    @Override
    public void updateUserPerms(Integer uid, String ids) {
        if (ids ==null || "".equals(ids)){
            roleDao.updateUserRole(uid,3);
        }else {
            String[] split = ids.split(",");
            Integer[] arr = new Integer[split.length];
            for (int i=0;i<split.length;i++){
                arr[i] = Integer.valueOf(split[i]);
            }
            User user = userDao.findUserById1(uid);
            Role role = new Role();
            role.setName(user.getuName());
            //插入新角色
            roleDao.insertRole(role);
            //修改用户的角色
            roleDao.updateUserRole(uid,role.getRid());
            //修改权限
            for (Integer i:arr){
                roleDao.insertIntoRolePerm(role.getRid(),i);
            }
        }
    }

    @Override
    public List<Permission> findRolePermByRid(Integer rid) {
        List<Permission> list = permissionDao.findRolePermsByRid(rid);
        return list;
    }

    @Override
    public void updateRolePerms(Integer rid, String str) {
        roleDao.deleteRolePerm(rid);
        if (str == null || "".equals(str)){
            return;
        }else {
            String[] ids = str.split(",");
            for (int i=0;i<ids.length;i++){
                roleDao.insertIntoRolePerm(rid,Integer.valueOf(ids[i]));
            }
        }

    }


}
