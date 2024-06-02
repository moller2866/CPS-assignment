package dk.sdu.cps.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;
import dk.sdu.cps.backend.dto.decorator.FahrenheitDecorator;
import dk.sdu.cps.backend.exceptions.LocationNotFoundException;
import dk.sdu.cps.backend.repository.WeatherMeasurementRepository;

@Service
public class WeatherMeasurementService {
    private final WeatherMeasurementRepository weatherRepository;
    private final LocationService locationService;

    public WeatherMeasurementService(
            WeatherMeasurementRepository weatherRepository, LocationService locationService) {
        this.weatherRepository = weatherRepository;
        this.locationService = locationService;
    }

    public List<IWeatherMeasurementDTO> getWeatherDataSince(LocalDateTime time, String name)
            throws LocationNotFoundException {
        if (locationService.getLocation(name) == null) {
            throw new LocationNotFoundException(name);
        }
        return weatherRepository.getSinceFromLocation(time, name);
    }

    public List<IWeatherMeasurementDTO> getAllFromLocation(String name, String unit)
            throws LocationNotFoundException {
        if (locationService.getLocation(name) == null) {
            throw new LocationNotFoundException(name);
        }
        if (unit.equals("fahrenheit")) {
            return weatherRepository.getAllFromLocation(name).stream()
                    .map(FahrenheitDecorator::new)
                    .collect(Collectors.toList());
        }
        return weatherRepository.getAllFromLocation(name);
    }

    public IWeatherMeasurementDTO getLatestFromLocation(String name, String unit)
            throws LocationNotFoundException {
        if (locationService.getLocation(name) == null) {
            throw new LocationNotFoundException(name);
        }
        if (unit.equals("fahrenheit")) {
            return new FahrenheitDecorator(weatherRepository.getLatestFromLocation(name));
        }
        return weatherRepository.getLatestFromLocation(name);
    }
}
