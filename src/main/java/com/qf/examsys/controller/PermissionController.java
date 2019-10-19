package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Permission;
import com.qf.examsys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @RequestMapping("/showIndexMenus.do")
    public JsonReasult showIndexMenus(Integer uid) {
        List<Map<String, Object>> menus = permissionService.findMenus(uid);
        return new JsonReasult(0, menus);
    }

    @RequestMapping("/findAllPermissions.do")
    public JsonReasult findAllPermissions() {
        List<Permission> all = permissionService.findAll();
        return new JsonReasult(0, all);
    }

    @RequestMapping("/showUserPermissions.do")
    public JsonReasult showUserPermissions(Integer uid) {
        List<Permission> list = permissionService.findAllByUid(uid);
        return new JsonReasult(0, list);
    }

    @RequestMapping("/updatePermissions.do")
    public JsonReasult updatePermissions(Integer uid, String str) {
        try {
            permissionService.updateUserPerms(uid, str);
            return new JsonReasult(0, "success");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonReasult(1, e.getMessage());
        }
    }

    @RequestMapping("/showRolePermissions.do")
    public JsonReasult showRolePermissions(Integer rid) {

        List<Permission> list = permissionService.findRolePermByRid(rid);
        return new JsonReasult(0,list);

    }

    @RequestMapping("/updateRolePerm.do")
    public JsonReasult updateRolePerm(String str,Integer rid){

        permissionService.updateRolePerms(rid,str);
        return new JsonReasult(0,null);
    }


}