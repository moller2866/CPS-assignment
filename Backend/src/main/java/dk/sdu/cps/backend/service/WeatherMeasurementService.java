package dk.sdu.cps.backend.service;

import dk.sdu.cps.backend.repository.WeatherRecord;
import dk.sdu.cps.backend.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {
    private WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public WeatherRecord getLatestWeatherData() {
        return weatherRepository.getLatestWeatherData();
    }

    public List<WeatherRecord> getWeatherDataSince(LocalDateTime time) {
        return weatherRepository.getWeatherDataSince(time);
    }

    public List<WeatherRecord> getAll() {
        return weatherRepository.getAll();
    }

    public WeatherRecord getLatest() {
        return weatherRepository.getLatestWeatherData();
    }
}
