package dk.sdu.cps.backend.repository;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherRecord {
    LocalDateTime time;
    Double airTemperature;
    Double relativeHumidity;
    Double windSpeed;
    Double windFromDirection;
    Double windSpeedOfGust;
}
