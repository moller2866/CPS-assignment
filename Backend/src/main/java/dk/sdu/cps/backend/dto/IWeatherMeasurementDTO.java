package dk.sdu.cps.backend.dto;

import java.time.LocalDateTime;

public interface IWeatherMeasurementDTO {
    LocalDateTime getTimeStamp();

    String getLocationName();

    Float getTemperature();

    int getHumidity();

    Float getWindSpeed();

    Float getWindDirection();

    Float getWindOfGust();

    Float getPrecipitationAmount();

    String getNextHourSym();
}
