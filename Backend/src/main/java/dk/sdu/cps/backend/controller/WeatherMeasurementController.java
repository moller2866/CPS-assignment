package dk.sdu.cps.backend.controller;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;
import dk.sdu.cps.backend.dto.WeatherMeasurementDTO;
import dk.sdu.cps.backend.dto.decorator.FahrenheitDecorator;
import dk.sdu.cps.backend.exceptions.LocationNotFoundException;
import dk.sdu.cps.backend.service.WeatherMeasurementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherMeasurementController {

    private final WeatherMeasurementService weatherService;

    public WeatherMeasurementController(WeatherMeasurementService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/all")
    public List<IWeatherMeasurementDTO> getAllFromLocation(@RequestParam(value = "location") String location, @RequestParam(value = "unit", defaultValue = "celsius") String unit) {
        if (Objects.equals(unit, "fahrenheit")) {
            return weatherService.getAllFromLocation(location).stream().map(FahrenheitDecorator::new).collect(Collectors.toList());
        }
        return weatherService.getAllFromLocation(location);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<String> handleLocationNotFoundException(LocationNotFoundException e) {
        return new ResponseEntity<>("Location not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/since")
    public List<IWeatherMeasurementDTO> getWeatherMeasurementsSince(@RequestParam(value = "time") String time, @RequestParam(value = "location") String location) {
        LocalDateTime timestamp = LocalDateTime.parse(time);
        return weatherService.getWeatherDataSince(timestamp, location);
    }

    @GetMapping("/latest")
    public IWeatherMeasurementDTO getLatestWeatherMeasurement(@RequestParam(value = "location") String location, @RequestParam(value = "unit", defaultValue = "celsius") String unit) {
        if (Objects.equals(unit, "fahrenheit")) {
            return new FahrenheitDecorator(weatherService.getLatestFromLocation(location));
        }
        return weatherService.getLatestFromLocation(location);
    }

}
