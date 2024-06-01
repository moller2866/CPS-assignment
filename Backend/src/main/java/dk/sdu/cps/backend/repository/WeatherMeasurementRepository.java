package dk.sdu.cps.backend.repository;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;
import dk.sdu.cps.backend.dto.WeatherMeasurementDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherMeasurementRepository {
    void create(WeatherMeasurementDTO weatherMeasurementDTO);

    IWeatherMeasurementDTO getLatestFromLocation(String name);

    List<IWeatherMeasurementDTO> getSinceFromLocation(LocalDateTime time, String name);

    List<IWeatherMeasurementDTO> getAllFromLocation(String name);

}