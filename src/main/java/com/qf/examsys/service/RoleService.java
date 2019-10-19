package com.qf.examsys.service;

import com.qf.examsys.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRoles();

    void updateUserRole(Integer uid,Integer rid);

    void addRole(String name);

    Role findRoleById(Integer rid);

    void updateRoleName(Role role);

}
