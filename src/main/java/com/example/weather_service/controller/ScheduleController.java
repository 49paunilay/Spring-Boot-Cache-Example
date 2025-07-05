package com.example.weather_service.controller;

import com.example.weather_service.Entity.ScheduleConfig;
import com.example.weather_service.dto.ScheduleConfigRequest;
import com.example.weather_service.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/update-cron")
    public ResponseEntity<ScheduleConfig> updateSchedule(@RequestBody ScheduleConfigRequest scheduleConfigRequest){
        ScheduleConfig scheduleConfig = scheduleService.updateScheduleConfig(scheduleConfigRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(scheduleConfig);
    }
}
