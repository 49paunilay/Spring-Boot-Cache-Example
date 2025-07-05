package com.example.weather_service.service;

import com.example.weather_service.Entity.ScheduleConfig;
import com.example.weather_service.Repository.ScheduleConfigRepository;
import com.example.weather_service.dto.ScheduleConfigRequest;
import com.example.weather_service.schedulers.OrderScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleService {
    @Autowired
    ScheduleConfigRepository scheduleConfigRepository;

    @Autowired
    OrderScheduler orderScheduler;

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    ScheduledFuture<?> scheduledTask;

    public ScheduleService(){
        threadPoolTaskScheduler.initialize();
    }

    public ScheduleConfig updateScheduleConfig(ScheduleConfigRequest scheduleConfigRequest){
        ScheduleConfig scheduleConfig = scheduleConfigRepository.findByJobName(scheduleConfigRequest.getJobName()).orElse(new ScheduleConfig());
        scheduleConfig.setJobName(scheduleConfigRequest.getJobName());
        scheduleConfig.setCronExpression(scheduleConfigRequest.getCronExpression());
        scheduleConfigRepository.save(scheduleConfig);
        restartScheduledTask(scheduleConfigRequest.getCronExpression());
        return scheduleConfig;
    }

    private void restartScheduledTask(String cronExpression){
        if(scheduledTask!=null){
            scheduledTask.cancel(false);
        }
        scheduledTask = threadPoolTaskScheduler.schedule(orderScheduler::processPendingOrders,new CronTrigger(cronExpression));
    }
}
