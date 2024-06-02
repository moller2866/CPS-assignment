package dk.sdu.cps.backend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;
import dk.sdu.cps.backend.exceptions.LocationNotFoundException;
import dk.sdu.cps.backend.service.WeatherMeasurementService;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherMeasurementController {

    private final WeatherMeasurementService weatherService;

    public WeatherMeasurementController(WeatherMeasurementService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/all")
    public List<IWeatherMeasurementDTO> getAllFromLocation(
            @RequestParam(value = "location") String location,
            @RequestParam(value = "unit", defaultValue = "celsius") String unit) {
        return weatherService.getAllFromLocation(location, unit);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<String> handleLocationNotFoundException(LocationNotFoundException e) {
        return new ResponseEntity<>("Location not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/since")
    public List<IWeatherMeasurementDTO> getWeatherMeasurementsSince(
            @RequestParam(value = "time") String time,
            @RequestParam(value = "location") String location) {
        LocalDateTime timestamp = LocalDateTime.parse(time);
        return weatherService.getWeatherDataSince(timestamp, location);
    }

    @GetMapping("/latest")
    public IWeatherMeasurementDTO getLatestWeatherMeasurement(
            @RequestParam(value = "location") String location,
            @RequestParam(value = "unit", defaultValue = "celsius") String unit) {
        return weatherService.getLatestFromLocation(location, unit);
    }
}
