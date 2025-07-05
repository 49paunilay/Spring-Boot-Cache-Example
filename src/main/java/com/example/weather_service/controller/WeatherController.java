package com.example.weather_service.controller;

import com.example.weather_service.Entity.Weather;
import com.example.weather_service.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping
    public String getWeather(@RequestParam String city){
        return weatherService.getWeather(city);
    }

    @PostMapping
    public Weather addWeather(@RequestBody Weather weather){
        return weatherService.saveWeather(weather);
    }

    @GetMapping("/all")
    public List<Weather> getAllWeathers(){
        return weatherService.getAllWeathers();
    }

    @PutMapping("/{cityName}")
    public String updateWeather(@PathVariable String cityName, @RequestParam String updatedForecast){
        return weatherService.updateWeather(cityName,updatedForecast);
    }

    @DeleteMapping("/{city}")
    public String deleteByCityName(@PathVariable String city){
        return weatherService.deleteByCityName(city);
    }
}
