package com.qf.examsys.controller;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.entity.Menu;
import com.qf.examsys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/menus.do")
    @ResponseBody
    public JsonReasult menus(Integer uid){
        List<Menu> menus = menuService.findAllMenus(uid);
        JsonReasult<Object> jsonReasult = new JsonReasult<>();
        return new JsonReasult(1,menus);
    }

    @RequestMapping("/showUserPerms.do")
    @ResponseBody
    public JsonReasult showUserPerms(Integer uid){
        List<Map<String, Object>> maps = menuService.findAllPerms(uid);
        return new JsonReasult(0,maps);
    }

    @RequestMapping("/showUserPermsPlus.do")
    @ResponseBody
    public JsonReasult showUserPermsPlus(Integer uid){
        Map<String, List<Map<String, Object>>> all = menuService.findAll(uid);
        return new JsonReasult(0,all);
    }

}
