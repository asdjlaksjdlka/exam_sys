package com.qf.examsys.service.impl;

import com.qf.examsys.dao.PoiDao;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoiServiceImpl implements PoiService {

    @Autowired(required = false)
    private PoiDao poiDao;


    @Override
    public List<Choose> findAllChooseByPoi(String cTitle, Integer sid) {
        return poiDao.findAllChooseByPoi(cTitle,sid);
    }

    @Override
    public int addQuestionChoose(List<Choose> chooses) {

        return poiDao.addChooses(chooses);
    }


    @Override
    public int addChooses(List<Choose> chooses) {

        for (Choose choose : chooses) {
            choose.setSid(poiDao.findSubjectByName(choose.getSubject().getsName()).getSid());
        }

        return poiDao.addChooses(chooses);
    }
}
