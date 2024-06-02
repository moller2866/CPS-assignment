package dk.sdu.cps.backend.dto.decorator;

import java.time.LocalDateTime;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;

public abstract class WeatherMeasurementDecorator implements IWeatherMeasurementDTO {

    private IWeatherMeasurementDTO weatherMeasurementDTO;

    public WeatherMeasurementDecorator(IWeatherMeasurementDTO weatherMeasurementDTO) {
        this.weatherMeasurementDTO = weatherMeasurementDTO;
    }

    @Override
    public String getLocationName() {
        return weatherMeasurementDTO.getLocationName();
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return weatherMeasurementDTO.getTimeStamp();
    }

    @Override
    public Float getTemperature() {
        return weatherMeasurementDTO.getTemperature();
    }

    @Override
    public int getHumidity() {
        return weatherMeasurementDTO.getHumidity();
    }

    @Override
    public Float getWindSpeed() {
        return weatherMeasurementDTO.getWindSpeed();
    }

    @Override
    public Float getWindDirection() {
        return weatherMeasurementDTO.getWindDirection();
    }

    @Override
    public Float getWindOfGust() {
        return weatherMeasurementDTO.getWindOfGust();
    }

    @Override
    public Float getPrecipitationAmount() {
        return weatherMeasurementDTO.getPrecipitationAmount();
    }

    @Override
    public String getNextHourSym() {
        return weatherMeasurementDTO.getNextHourSym();
    }
}
