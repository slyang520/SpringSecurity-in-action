package com.example.slyangsecurity.config.quartz;

import com.alibaba.fastjson.JSON;
import com.example.slyangsecurity.modules.block.dao.BcChaincodeMapper;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class DynamicJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(DynamicJob.class);

    @Autowired
    BcChaincodeMapper bcChaincodeMapper;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        bcChaincodeMapper.selectById(1);
        logger.info(JSON.toJSONString(context.getMergedJobDataMap()));
        logger.info("i am excute   hehheheh ");

    }

}
