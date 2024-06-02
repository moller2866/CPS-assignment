package dk.sdu.cps.backend.service;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;
import dk.sdu.cps.backend.dto.LocationDTO;
import dk.sdu.cps.backend.dto.WeatherMeasurementDTO;
import dk.sdu.cps.backend.exceptions.LocationNotFoundException;
import dk.sdu.cps.backend.repository.WeatherMeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherMeasurementServiceTest {
    private WeatherMeasurementRepository weatherRepository;
    private LocationService locationService;
    private WeatherMeasurementService weatherMeasurementService;

    @BeforeEach
    void setUp() {
        weatherRepository = mock(WeatherMeasurementRepository.class);
        locationService = mock(LocationService.class);
        weatherMeasurementService = new WeatherMeasurementService(weatherRepository, locationService);
    }

    @Test
    void getWeatherDataSince() throws LocationNotFoundException {
        LocalDateTime time = LocalDateTime.now();
        String locationName = "TestLocation";
        IWeatherMeasurementDTO weatherMeasurementDTO = WeatherMeasurementDTO.builder().build();
        when(locationService.getLocation(locationName)).thenReturn(new LocationDTO());
        when(weatherRepository.getSinceFromLocation(time, locationName)).thenReturn(Collections.singletonList(weatherMeasurementDTO));
        List<IWeatherMeasurementDTO> result = weatherMeasurementService.getWeatherDataSince(time, locationName);
        assertEquals(1, result.size());
        assertEquals(weatherMeasurementDTO, result.get(0));
    }

    @Test
    void getAllFromLocation() throws LocationNotFoundException {
        String locationName = "TestLocation";
        IWeatherMeasurementDTO weatherMeasurementDTO = WeatherMeasurementDTO.builder().build();
        when(locationService.getLocation(locationName)).thenReturn(new LocationDTO());
        when(weatherRepository.getAllFromLocation(locationName)).thenReturn(Collections.singletonList(weatherMeasurementDTO));

        List<IWeatherMeasurementDTO> result = weatherMeasurementService.getAllFromLocation(locationName, "celsius");

        assertEquals(1, result.size());
        assertEquals(weatherMeasurementDTO, result.get(0));
    }

    @Test
    void getLatestFromLocation() throws LocationNotFoundException {
        String locationName = "TestLocation";
        IWeatherMeasurementDTO weatherMeasurementDTO = WeatherMeasurementDTO.builder().build();
        when(locationService.getLocation(locationName)).thenReturn(new LocationDTO());
        when(weatherRepository.getLatestFromLocation(locationName)).thenReturn(weatherMeasurementDTO);

        IWeatherMeasurementDTO result = weatherMeasurementService.getLatestFromLocation(locationName, "celsius");

        assertEquals(weatherMeasurementDTO, result);
    }
}