package dk.sdu.cps.backend.repository.impl;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;
import dk.sdu.cps.backend.dto.WeatherMeasurementDTO;
import dk.sdu.cps.backend.repository.WeatherMeasurementRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherMeasurementRepositoryImplTest {
    private WeatherMeasurementRepository weatherMeasurementRepository;

    public WeatherMeasurementRepositoryImplTest(WeatherMeasurementRepository weatherMeasurementRepository) {
        this.weatherMeasurementRepository = weatherMeasurementRepository;
    }

    @Test
    public void testGetLatestFromLocation() {
        String locationName = "Odense";
        IWeatherMeasurementDTO latestMeasurement = weatherMeasurementRepository.getLatestFromLocation(locationName);

        assertNotNull(latestMeasurement);
        assertEquals(locationName, latestMeasurement.getLocationName());
        // Add more assertions based on your test data
    }
}