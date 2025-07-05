package com.example.weather_service.service;

import com.example.weather_service.Entity.Weather;
import com.example.weather_service.Repository.WeatherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    WeatherRepository weatherRepository;

    @Autowired
    WeatherService(WeatherRepository weatherRepository){
        this.weatherRepository = weatherRepository;
    }

    @Cacheable(value = "weather",key = "#city")
    public String getWeather(String city){
        Optional<Weather> optionalWeather =  this.weatherRepository.findByCity(city);
        if(optionalWeather.isPresent()){
            return optionalWeather.get().getForecast();
        }else{
            return "No city found with given name";
        }
    }

    public Weather saveWeather(Weather weather){
        return this.weatherRepository.save(weather);
    }

    public List<Weather> getAllWeathers(){
        return this.weatherRepository.findAll();
    }

    @CachePut(value = "weather",key = "#city")
    public String updateWeather(String city,String updatedWeather){
        this.weatherRepository.findByCity(city).ifPresent(weather -> {
            weather.setForecast(updatedWeather);
            this.weatherRepository.save(weather);
        });
        return updatedWeather;
    }

    @Transactional
    @CacheEvict(value = "weather",key = "#city")
    public String deleteByCityName(String city) {
        this.weatherRepository.deleteByCity(city);
        return "City "+city+" is deleted";
    }
}
