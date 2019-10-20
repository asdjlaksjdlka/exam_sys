package com.qf.examsys.service.impl;

import com.qf.examsys.dao.RoleDao;
import com.qf.examsys.entity.Role;
import com.qf.examsys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired(required = false)
    RoleDao roleDao;

    @Override
    public List<Role> findAllRoles() {

        List<Role> list = roleDao.findAllRoles();

        return list;
    }

    @Override
    public void updateUserRole(Integer uid, Integer rid) {
        if (rid == null || "".equals(rid+"")){
            return;
        }
        roleDao.updateUserRole(uid,rid);
    }

    @Override
    public void addRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleDao.insertRole(role);
    }



    @Override
    public Role findRoleById(Integer rid) {
        Role role = roleDao.findRoleById(rid);
        return role;
    }

    @Override
    public void updateRoleName(Role role) {
        roleDao.updateRoleName(role);
    }
}
