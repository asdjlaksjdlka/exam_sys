package com.qf.examsys.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.examsys.dao.BriefQuestionDao;
import com.qf.examsys.dao.ChooseQuestionDao;
import com.qf.examsys.entity.Brief;
import com.qf.examsys.service.BriefQuestionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiresPermissions("import:question")
@Service
public class BriefQuestionServiceImpl implements BriefQuestionService {

    @Autowired(required = false)
    private BriefQuestionDao briefQuestionDao;


    @Override
    public List<Brief> findAllBrief(Map<String, Integer> pageMap, String bTitle, Integer sid) {
        PageHelper.startPage(pageMap.get("page"), pageMap.get("limit"));
        return briefQuestionDao.findAllBrief(bTitle,sid);
    }

    @Override
    public Brief findBriefQuestionById(Integer bid) {
        return briefQuestionDao.findBriefQuestionById(bid);
    }

    @Override
    public Integer updateBriefById(Brief brief) {
        return briefQuestionDao.updateBriefById(brief);
    }

    @Override
    public Integer addBrief(Brief brief) {
        return briefQuestionDao.addBrief(brief);
    }

    @Override
    public Integer deleteOneById(Brief brief) {
        return briefQuestionDao.deleteOneById(brief);
    }

    @Override
    public Integer deletAllBrief(Integer[] ids) {
        return briefQuestionDao.deletAllBrief(ids);
    }

    @Override
    public List<Brief> findAllBriefByPoi(String bTitle, Integer sid) {
        return briefQuestionDao.findAllBrief(bTitle,sid);
    }

    @Override
    public int addBrief(List<Brief> briefs) {
        return briefQuestionDao.addBriefs(briefs);
    }

}
