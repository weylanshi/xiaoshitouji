package com.weylan.blog.service.impl;

import com.weylan.blog.entity.Essay;
import com.weylan.blog.mapper.EssayMapper;
import com.weylan.blog.service.EssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;


@Service
public class EssayServiceImpl implements EssayService {

    @Autowired
    private EssayMapper essayMapper;

    private  ExecutorService executorService = Executors.newFixedThreadPool(10);



    @Override
    public Essay getEssayById(String essayId) {
        Essay essay = essayMapper.selectByPrimaryKey(essayId);
        if (essay == null) {
            return null;
        }
        executorService.execute(() -> asyncUpdateEssayFootPrint(essayId));
        return essay;
    }

    private void asyncUpdateEssayFootPrint(String essayId) {
        Essay essay = essayMapper.selectByPrimaryKey(essayId);
        Integer footprintCount = essay.getEssayFootprintCount() + 1;
        essay.setEssayFootprintCount(footprintCount);
        essayMapper.updateByPrimaryKey(essay);
    }
}
