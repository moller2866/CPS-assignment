package dk.sdu.cps.backend.service;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;
import dk.sdu.cps.backend.dto.WeatherMeasurementDTO;
import dk.sdu.cps.backend.exceptions.LocationNotFoundException;
import dk.sdu.cps.backend.repository.WeatherMeasurementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherMeasurementService {
    private final WeatherMeasurementRepository weatherRepository;
    private final LocationService locationService;

    public WeatherMeasurementService(WeatherMeasurementRepository weatherRepository, LocationService locationService) {
        this.weatherRepository = weatherRepository;
        this.locationService = locationService;
    }

    public List<IWeatherMeasurementDTO> getWeatherDataSince(LocalDateTime time, String name) throws LocationNotFoundException {
        if (locationService.getLocation(name) == null) {
            throw new LocationNotFoundException(name);
        }
        return weatherRepository.getSinceFromLocation(time, name);
    }

    public List<IWeatherMeasurementDTO> getAllFromLocation(String name) throws LocationNotFoundException {
        if (locationService.getLocation(name) == null) {
            throw new LocationNotFoundException(name);
        }
        return weatherRepository.getAllFromLocation(name);
    }

    public IWeatherMeasurementDTO getLatestFromLocation(String name) throws LocationNotFoundException {
        if (locationService.getLocation(name) == null) {
            throw new LocationNotFoundException(name);
        }
        return weatherRepository.getLatestFromLocation(name);
    }
}
