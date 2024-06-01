package dk.sdu.cps.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherDTO {
    LocalDateTime time;
    Double airTemperature;
    Double percipitationAmount;
    Double relativeHumidity;
    Double windSpeed;
    Double windFromDirection;
    Double windSpeedOfGust;
}
