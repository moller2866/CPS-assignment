package dk.sdu.cps.backend.external;

import dk.sdu.cps.backend.dto.LocationDTO;
import dk.sdu.cps.backend.dto.WeatherMeasurementDTO;
import dk.sdu.cps.backend.external.weatherResponseModel.Details;
import dk.sdu.cps.backend.external.weatherResponseModel.Next_1_hours;
import dk.sdu.cps.backend.repository.LocationRepository;
import dk.sdu.cps.backend.repository.impl.WeatherMeasurementRepositoryImpl;
import dk.sdu.cps.backend.external.weatherResponseModel.Timeseries;
import dk.sdu.cps.backend.external.weatherResponseModel.WeatherResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Component
public class WeatherDataComponent {

    private final RestTemplate restTemplate;
    private final WeatherMeasurementRepositoryImpl weatherRepository;
    private final LocationRepository locationRepository;

    public WeatherDataComponent(RestTemplate restTemplate, WeatherMeasurementRepositoryImpl weatherRepository, LocationRepository locationRepository) {
        this.restTemplate = restTemplate;
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
        this.restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Content-Type", "application/json");
            request.getHeaders().add("User-Agent", "Test-App");
            return execution.execute(request, body);
        });
    }

    @Scheduled(fixedRate = 10 * 60 * 1000) // 10 minutes
    public void scheduledLoop() {
        List<LocationDTO> locations = locationRepository.getAll();
        locations.forEach(this::FetchAndSaveMeasurement);
    }

    public void FetchAndSaveMeasurement(LocationDTO location) {
        String url = "https://api.met.no/weatherapi/nowcast/2.0/complete.json?lat=" + location.getLatitude() + "&lon=" + location.getLongitude();
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        assert response != null;
        Timeseries firstEntry = response.properties().timeseries().get(0);
        LocalDateTime time = Instant.parse(firstEntry.time()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        Details details = firstEntry.data().instant().details();
        Next_1_hours next1Hours = firstEntry.data().next_1_hours();
        WeatherMeasurementDTO latestWeatherData = weatherRepository.getLatestFromLocation(location.getName());
        if (latestWeatherData != null && latestWeatherData.getTimeStamp().equals(time)) {
            return;
        }
        WeatherMeasurementDTO weatherData = getWeatherMeasurementDTO(time, details, next1Hours, location.getName());
        weatherRepository.create(weatherData);
    }

    private static WeatherMeasurementDTO getWeatherMeasurementDTO(LocalDateTime time, Details details, Next_1_hours next1Hours, String locationName) {
        return WeatherMeasurementDTO.builder()
                .setLocationName(locationName)
                .setTimeStamp(time)
                .setTemperature(details.air_temperature())
                .setHumidity(details.relative_humidity())
                .setWindDirection(details.wind_from_direction())
                .setWindSpeed(details.wind_speed())
                .setWindOfGust(details.wind_speed_of_gust())
                .setPrecipitationAmount(next1Hours.details().precipitation_amount())
                .setNextHourSym(next1Hours.summary().symbol_code())
                .build();
    }

}