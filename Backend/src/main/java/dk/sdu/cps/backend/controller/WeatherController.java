package dk.sdu.cps.backend.controller;

import dk.sdu.cps.backend.repository.WeatherRecord;
import dk.sdu.cps.backend.service.WeatherService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public WeatherRecord getWeather() {
        return weatherService.getLatest();
    }

    @GetMapping("/weather/all")
    public List<WeatherRecord> getAllWeather() {
        return weatherService.getAll();
    }

}