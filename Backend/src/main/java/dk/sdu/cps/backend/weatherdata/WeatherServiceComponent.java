package dk.sdu.cps.backend.weatherdata;

import dk.sdu.cps.backend.weatherdata.WeatherData;
import dk.sdu.cps.backend.weatherdata.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceComponent {

    private final WeatherRepository weatherRepository;

    public WeatherServiceComponent(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public List<WeatherData> getWeatherData() {
        return weatherRepository.findAll();
    }
}