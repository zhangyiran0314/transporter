package com.ifly.transporter.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务配置
 * 1.cron从左起表示：*(�?-59) *(分钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT) 
 * 2.月份和星期中对日期的定义相排�?,不能并列出现月份和星期的定义,�?��定义其中�?���??"
 * @author zhangguan
 */
@Configuration
@EnableScheduling // 启用定时任务
public class ScheduleConfig {
	private final static Logger LOGGER = LoggerFactory.getLogger(ScheduleConfig.class);
	

    @Scheduled(cron = "0 0 0 * * ?") // 0点执�?
    public void refreshModel() {
    	LOGGER.info(" scheduled refreshModel start... ");
    	LOGGER.info(" scheduled refreshModel end... ");
    }
}
