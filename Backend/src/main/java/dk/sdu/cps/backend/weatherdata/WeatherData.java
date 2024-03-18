package dk.sdu.cps.backend.weatherdata;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "weather_data")
@Data
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    private Double airTemperature;
    private Double relativeHumidity;
    private Double windFromDirection;
    private Double windSpeed;
    private Double windSpeedOfGust;
    private String city;
}