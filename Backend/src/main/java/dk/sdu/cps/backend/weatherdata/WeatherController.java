package dk.sdu.cps.backend.weatherdata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherServiceComponent weatherServiceComponent;

    public WeatherController(WeatherServiceComponent weatherServiceComponent) {
        this.weatherServiceComponent = weatherServiceComponent;
    }

    @GetMapping("/weather")
    public List<WeatherData> getWeatherData() {
        return weatherServiceComponent.getWeatherData();
    }
}