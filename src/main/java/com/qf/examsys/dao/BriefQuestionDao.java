package com.qf.examsys.dao;

import com.qf.examsys.entity.Brief;
import com.qf.examsys.entity.Choose;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.subject.Subject;

import java.util.List;

public interface BriefQuestionDao {

    /**
     * 模糊查询加分页
     * @param bTitle
     * @param sid
     * @return
     */
    public List<Brief> findAllBrief(@Param("bTitle") String bTitle, @Param("sid") Integer sid);


    //修改回显
    public Brief findBriefQuestionById(Integer bid);

    //提交修改
    public Integer updateBriefById(Brief brief);

    /**
     * 添加简答题
     */
    public Integer addBrief(Brief brief);
    //删除题目
    public Integer deleteOneById(Brief brief);
    //批量删除
    public Integer deletAllBrief(Integer[] ids);

    /*
     * 导入导出
     * */

    public int addBriefs(@Param("briefs") List<Brief> briefs);

}
