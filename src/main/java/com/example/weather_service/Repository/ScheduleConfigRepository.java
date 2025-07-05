package com.example.weather_service.Repository;

import com.example.weather_service.Entity.ScheduleConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleConfigRepository extends JpaRepository<ScheduleConfig,Long> {

    Optional<ScheduleConfig> findByJobName(String jobName);
}
