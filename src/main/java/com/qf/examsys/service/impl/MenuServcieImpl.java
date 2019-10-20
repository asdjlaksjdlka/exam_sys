package com.qf.examsys.service.impl;

import com.qf.examsys.common.JsonReasult;
import com.qf.examsys.dao.MenuDao;
import com.qf.examsys.entity.Menu;
import com.qf.examsys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Service
public class MenuServcieImpl implements MenuService {

    @Autowired(required = false)
    MenuDao menuDao;

    @Override
    public List<Menu> findAllMenus(Integer uid) {

        List<Menu> firstMenus = menuDao.findAllFirstMenus(uid);
        for (Menu m:firstMenus){
            m.setMenus(menuDao.findAllSecMenus(uid,m.getPeid()));
        }
        return firstMenus;
    }

    @Override
    public List<Map<String, Object>> findAllPerms(Integer uid) {
        List<Menu> menus = menuDao.findAllFirstMenus(uid);
        List<Map<String,Object>> maps = new ArrayList<>();
        for (Menu m:menus){
            Map<String, Object> map = new HashMap<>();
            map.put("name",m.getpName());
            List<String> list = menuDao.findAllSecondPerms(uid, m.getPeid());
            List<Map<String,Object>> cmaps = new ArrayList<>();
            for (String s:list){

                Map<String,Object> cmap = new HashMap<>();
                cmap.put("name",s);
                cmaps.add(cmap);
            }
            map.put("children",cmaps);
            maps.add(map);
        }
        return maps;
    }

    @Override
    public Map<String, List<Map<String, Object>>> findAll(Integer uid) {

        Map<String, List<Map<String, Object>>> maps = new HashMap<>();

        //用户的
        List<Map<String, Object>> idList = findAllPerms(uid);
        //第一个
        maps.put("userPerms",idList);

        List<String> allPerms = menuDao.findAllPerm();
        List<Map<String,Object>> list = new ArrayList<>();
        for (String str:allPerms){
            //放一组
            Map<String, Object> map = new HashMap<>();
            //放父节点
            map.put("name",str);
            //查询子节点
            List<String> permsByName = menuDao.findPermsByName(str);
            //子节点格式 name
            List<Map<String,Object>> cmaps = new ArrayList<>();
            //将child 格式化为 name value
            for (String s:permsByName){
                Map<String,Object> cmap = new HashMap<>(5);
                cmap.put("name",s);
                cmaps.add(cmap);
            }
            map.put("children",cmaps);
            list.add(map);
        }
        maps.put("allPerms",list);
        return maps;
    }


}
