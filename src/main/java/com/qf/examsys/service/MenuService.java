package com.qf.examsys.service;

import com.qf.examsys.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    List<Menu> findAllMenus(Integer uid);

    List<Map<String,Object>> findAllPerms(Integer uid);

    Map<String,List<Map<String,Object>>> findAll(Integer uid);
}
