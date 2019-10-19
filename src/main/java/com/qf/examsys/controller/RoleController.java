package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Role;
import com.qf.examsys.service.RoleService;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@ResponseBody
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/findAllRoles.do")
    public JsonReasult findAllRoles() {

        List<Role> list = roleService.findAllRoles();
        return new JsonReasult(0, list);
    }

    @RequestMapping("/updateUserRole.do")
    public JsonReasult updateUserRole(Integer rid, Integer uid) {
        roleService.updateUserRole(uid, rid);
        return new JsonReasult(0, "");
    }

    @RequestMapping("/addRole.do")
    public JsonReasult addRole(String name) {
        roleService.addRole(name);
        return new JsonReasult(0, "");
    }

    @RequestMapping("/addPermission.do")
    public JsonReasult addPermission(Integer rid, String str) {

        return new JsonReasult();
    }

    @RequestMapping("/findRoleById.do")
    public JsonReasult findRoleById(Integer rid) {
        Role role = roleService.findRoleById(rid);
        return new JsonReasult(0, role);
    }

    @RequestMapping("/updateRoleName.do")
    public JsonReasult updateRoleName(Role role) {

        System.out.println(role.getName());
        roleService.updateRoleName(role);
        return new JsonReasult(0, "");
    }


}
