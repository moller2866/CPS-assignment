package dk.sdu.cps.backend.external.weatherResponseModel;

public record Details(
        Float air_temperature,
        int relative_humidity,
        Float wind_from_direction,
        Float wind_speed,
        Float wind_speed_of_gust
) {}
