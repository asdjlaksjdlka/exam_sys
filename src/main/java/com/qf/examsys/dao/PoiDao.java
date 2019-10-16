package com.qf.examsys.dao;

import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoiDao {
    public List<Choose> findAllChooseByPoi(@Param("cTitle") String cTitle, @Param("sid") Integer sid);

    public int addChooses(@Param("chooses") List<Choose> chooses);

    public Subject findSubjectByName(String sName);
}
