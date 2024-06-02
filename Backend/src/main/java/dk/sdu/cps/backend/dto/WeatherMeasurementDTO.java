package dk.sdu.cps.backend.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;

@Getter
public class WeatherMeasurementDTO implements IWeatherMeasurementDTO {
    LocalDateTime timeStamp;
    String locationName;
    Float temperature;
    int humidity;
    Float windSpeed;
    Float windDirection;
    Float windOfGust;
    Float precipitationAmount;
    String nextHourSym;

    private WeatherMeasurementDTO(Builder builder) {
        this.timeStamp = builder.timeStamp;
        this.locationName = builder.locationName;
        this.temperature = builder.temperature;
        this.humidity = builder.humidity;
        this.windSpeed = builder.windSpeed;
        this.windDirection = builder.windDirection;
        this.windOfGust = builder.windOfGust;
        this.precipitationAmount = builder.precipitationAmount;
        this.nextHourSym = builder.nextHourSym;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Data
    public static class Builder {
        private LocalDateTime timeStamp;
        private String locationName;
        private Float temperature;
        private int humidity;
        private Float windSpeed;
        private Float windDirection;
        private Float windOfGust;
        private Float precipitationAmount;
        private String nextHourSym;

        public Builder setTimeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder setLocationName(String locationName) {
            this.locationName = locationName;
            return this;
        }

        public Builder setTemperature(Float temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder setHumidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder setWindSpeed(Float windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public Builder setWindDirection(Float windDirection) {
            this.windDirection = windDirection;
            return this;
        }

        public Builder setWindOfGust(Float windOfGust) {
            this.windOfGust = windOfGust;
            return this;
        }

        public Builder setPrecipitationAmount(Float precipitationAmount) {
            this.precipitationAmount = precipitationAmount;
            return this;
        }

        public Builder setNextHourSym(String nextHourSym) {
            this.nextHourSym = nextHourSym;
            return this;
        }

        public WeatherMeasurementDTO build() {
            return new WeatherMeasurementDTO(this);
        }
    }
}
