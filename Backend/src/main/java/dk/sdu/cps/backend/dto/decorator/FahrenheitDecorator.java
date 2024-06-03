package dk.sdu.cps.backend.dto.decorator;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;

public class FahrenheitDecorator extends WeatherMeasurementDecorator {

    public FahrenheitDecorator(IWeatherMeasurementDTO weatherMeasurementDTO) {
        super(weatherMeasurementDTO);
    }

    @Override
    public Float getTemperature() {
        return covertToFarhenheit(super.getTemperature());
    }

    private Float covertToFarhenheit(Float temperature) {
        return (temperature * 9 / 5) + 32;
    }
}
