package dk.sdu.cps.backend.dto.decorator;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;

public class FahrenheitDecorator extends WeatherMeasurementDecorator {

    public FahrenheitDecorator(IWeatherMeasurementDTO weatherMeasurementDTO) {
        super(weatherMeasurementDTO);
    }

    @Override
    public Float getTemperature() {
        return (super.getTemperature() * 9 / 5) + 32;
    }
}
