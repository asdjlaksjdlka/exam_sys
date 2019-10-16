package com.qf.examsys.service;

import com.qf.examsys.entity.Choose;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoiService {

    public List<Choose> findAllChooseByPoi(@Param("cTitle") String cTitle, @Param("sid") Integer sid);
    //批量上传excel
    public int addChooses(@Param("chooses") List<Choose> chooses);


    public int addQuestionChoose(@Param("chooses") List<Choose> chooses);

}
