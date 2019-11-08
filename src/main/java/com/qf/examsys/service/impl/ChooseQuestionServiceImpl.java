package com.qf.examsys.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.examsys.dao.ChooseQuestionDao;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;
import com.qf.examsys.service.ChooseQuestionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@RequiresPermissions("import:question")
@Service
public class ChooseQuestionServiceImpl implements ChooseQuestionService {

    @Autowired(required = false)
    private ChooseQuestionDao chooseQuestionDao;

    @Override
    public List<Subject> findAllSubject() {
        return chooseQuestionDao.findAllSubject();
    }

    @Override
    public List<Choose> findAllChoose(Map<String, Integer> pageMap,String cTitle,Integer sid) {
        // 设置页码和每页显示的记录数，该语句后面，紧跟着数据库查询相关的语句
//        System.out.println("ChooseQuestionServiceImpl标记一下"+chooseQuestionDao.findAllChoose(cTitle,sid));

        PageHelper.startPage(pageMap.get("page"), pageMap.get("limit"));
        return chooseQuestionDao.findAllChoose(cTitle,sid);
    }

    /*
    * 回显
    * */
    @Override
    public Choose findQuestionById(Integer cid) {
        return chooseQuestionDao.findQuestionById(cid);
    }

    @Override
    public Integer updateQuestionById(Choose choose) {
        return chooseQuestionDao.updateQuestionById(choose);
    }

    @Override
    public Integer deleteOneById(Choose choose) {
        return chooseQuestionDao.deleteOneById(choose);
    }

    @Override
    public Integer deletAlluQestion(Integer[] ids) {
        return chooseQuestionDao.deletAllQuestion(ids);
    }

    @Override
    public Integer addQuestion(Choose choose) {
        return chooseQuestionDao.addQuestion(choose);
    }


    /**
     *
     * 导入导出
     *
     */

    @Override
    public List<Choose> findAllChooseByPoi(String cTitle, Integer sid) {
        return chooseQuestionDao.findAllChoose(cTitle,sid);
    }

    @Override
    public int addQuestionChoose(List<Choose> chooses) {

        return chooseQuestionDao.addChooses(chooses);
    }



}