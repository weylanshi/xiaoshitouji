package com.weylan.blog.service.impl;

import com.weylan.blog.common.util.HtmlUtil;
import com.weylan.blog.entity.Essay;
import com.weylan.blog.entity.EssayDetail;
import com.weylan.blog.mapper.EssayDetailMapper;
import com.weylan.blog.mapper.EssayMapper;
import com.weylan.blog.mapper.UserMapper;
import com.weylan.blog.model.essay.EssayVo;
import com.weylan.blog.service.EssayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.*;


@Service
@Slf4j
public class EssayServiceImpl implements EssayService {

    @Autowired
    private EssayMapper essayMapper;

    @Autowired
    private EssayDetailMapper essayDetailMapper;

    @Autowired
    private UserMapper userMapper;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);


    @Override
    public void insertOrUpdateEssay(EssayVo essayVo) {
        Essay essay;
        String essayId = essayVo.getId();
        if (StringUtils.isEmpty(essayId)) {
            essay = new Essay();
        } else {
            essay = essayMapper.selectByPrimaryKey(essayId);
        }
        String detail = essayVo.getDetail();
        String summary = HtmlUtil.delHTMLTag(detail);
        if (summary.length() > 30) {
            summary = summary.substring(0, 30);
            summary += "...";
        }
        Date now = new Date();
        essay.setEssayDetail(summary);
        essay.setEssayMTime(now);
        essay.setEssayTitle(essayVo.getTitle());
        essay.setEssayUserId(essayVo.getUserId());
        essay.setEssayWordsCount(summary.length());
        if (StringUtils.isEmpty(essayId)) {
            essay.setEssayCTime(now);
            essay.setEssayFootprintCount(0);
            essay.setEssayStatus(1);
            essay.setEssayFavoriteCount(0);
            essayMapper.insert(essay);
            EssayDetail essayDetail = new EssayDetail();
            essayDetail.setId(essay.getId());
            essayDetail.setEssayDetail(HtmlUtil.delScriptTag(detail));
            essayDetailMapper.insert(essayDetail);
        } else {
            essayMapper.updateByPrimaryKey(essay);
            EssayDetail essayDetail = essayDetailMapper.selectByPrimaryKey(essayId);
            essayDetail.setId(essay.getId());
            essayDetail.setEssayDetail(HtmlUtil.delScriptTag(detail));
            essayDetailMapper.updateByPrimaryKey(essayDetail);
        }
    }

    @Override
    public Essay getEssayById(String essayId) {
        Essay essay = essayMapper.selectByPrimaryKey(essayId);
        if (essay == null) {
            return null;
        }
        EssayDetail essayDetail = essayDetailMapper.selectByPrimaryKey(essayId);
        essay.setEssayDetail(essayDetail.getEssayDetail());
        executorService.execute(() -> asyncUpdateEssayFootPrint(essayId));
        return essay;
    }

    //todo 添加防刷浏览量功能
    private void asyncUpdateEssayFootPrint(String essayId) {
        Essay essay = essayMapper.selectByPrimaryKey(essayId);
        Integer footprintCount = essay.getEssayFootprintCount() + 1;
        essay.setEssayFootprintCount(footprintCount);
        essayMapper.updateByPrimaryKey(essay);
    }

    public void asyncUpdateUserEssayCount(String userId) {
        
    }
}
