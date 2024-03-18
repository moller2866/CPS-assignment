package dk.sdu.cps.backend.weatherdata;

import dk.sdu.cps.backend.weatherdata.responseModel.Details;
import dk.sdu.cps.backend.weatherdata.responseModel.Timeseries;
import dk.sdu.cps.backend.weatherdata.responseModel.WeatherResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Component
public class WeatherDataComponent {

    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    public WeatherDataComponent(RestTemplate restTemplate, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplate;
        this.weatherRepository = weatherRepository;
        this.restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Content-Type", "application/json");
            request.getHeaders().add("User-Agent", "Test-App");
            return execution.execute(request, body);
        });
    }

    @Scheduled(fixedRate = 10 * 60 * 1000) // 10 minutes
    public void fetchAndSaveWeatherData() {
        String url = "https://api.met.no/weatherapi/nowcast/2.0/complete.json?lat=55.39594&lon=10.38831";
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
        assert response != null;
        Timeseries firstEntry = response.getProperties().getTimeseries().get(0);
        LocalDateTime time = Instant.parse(firstEntry.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        Optional<WeatherData> existingRecord = weatherRepository.findByTime(time);

        if (existingRecord.isPresent()) {
            return;
        }
        Details details = firstEntry.getData().getInstant().getDetails();
        WeatherData weatherData = new WeatherData();
        weatherData.setTime(time);
        weatherData.setAirTemperature(details.getAir_temperature());
        weatherData.setRelativeHumidity(details.getRelative_humidity());
        weatherData.setWindFromDirection(details.getWind_from_direction());
        weatherData.setWindSpeed(details.getWind_speed());
        weatherData.setWindSpeedOfGust(details.getWind_speed_of_gust());
        weatherData.setCity("Odense");
        weatherRepository.save(weatherData);
    }

}