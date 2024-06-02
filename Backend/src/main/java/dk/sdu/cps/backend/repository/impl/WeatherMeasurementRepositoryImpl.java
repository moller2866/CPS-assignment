package dk.sdu.cps.backend.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.generated.public_.tables.records.MeasurementRecord;
import org.springframework.stereotype.Repository;

import dk.sdu.cps.backend.dto.IWeatherMeasurementDTO;
import dk.sdu.cps.backend.dto.WeatherMeasurementDTO;
import dk.sdu.cps.backend.repository.WeatherMeasurementRepository;

import static org.jooq.generated.public_.tables.Measurement.MEASUREMENT;

@Repository
public class WeatherMeasurementRepositoryImpl implements WeatherMeasurementRepository {

    DSLContext dslContext;

    public WeatherMeasurementRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    private IWeatherMeasurementDTO measurementObject(MeasurementRecord record) {
        return new WeatherMeasurementDTO.Builder()
                .setTimeStamp(record.get(MEASUREMENT.TIME_STAMP))
                .setLocationName(record.get(MEASUREMENT.LOCATION_NAME))
                .setTemperature(record.get(MEASUREMENT.TEMPERATURE))
                .setHumidity(record.get(MEASUREMENT.HUMIDITY))
                .setWindSpeed(record.get(MEASUREMENT.WIND_SPEED))
                .setWindDirection(record.get(MEASUREMENT.WIND_DIRECTION))
                .setWindOfGust(record.get(MEASUREMENT.WIND_OF_GUST))
                .setPrecipitationAmount(record.get(MEASUREMENT.PRECIPITATION_AMOUNT))
                .setNextHourSym(record.get(MEASUREMENT.NEXT_HOUR_SYM))
                .build();
    }

    public IWeatherMeasurementDTO getLatestFromLocation(String name) {
        return dslContext
                .selectFrom(MEASUREMENT)
                .where(MEASUREMENT.LOCATION_NAME.eq(name))
                .orderBy(MEASUREMENT.TIME_STAMP.desc())
                .limit(1)
                .fetchOne(this::measurementObject);
    }

    public void create(WeatherMeasurementDTO weatherMeasurementDTO) {
        dslContext
                .insertInto(
                        MEASUREMENT,
                        MEASUREMENT.LOCATION_NAME,
                        MEASUREMENT.PRECIPITATION_AMOUNT,
                        MEASUREMENT.TIME_STAMP,
                        MEASUREMENT.TEMPERATURE,
                        MEASUREMENT.HUMIDITY,
                        MEASUREMENT.WIND_SPEED,
                        MEASUREMENT.WIND_DIRECTION,
                        MEASUREMENT.WIND_OF_GUST,
                        MEASUREMENT.NEXT_HOUR_SYM)
                .values(
                        weatherMeasurementDTO.getLocationName(),
                        weatherMeasurementDTO.getPrecipitationAmount(),
                        weatherMeasurementDTO.getTimeStamp(),
                        weatherMeasurementDTO.getTemperature(),
                        weatherMeasurementDTO.getHumidity(),
                        weatherMeasurementDTO.getWindSpeed(),
                        weatherMeasurementDTO.getWindDirection(),
                        weatherMeasurementDTO.getWindOfGust(),
                        weatherMeasurementDTO.getNextHourSym())
                .execute();
    }

    public List<IWeatherMeasurementDTO> getSinceFromLocation(LocalDateTime time, String name) {
        return dslContext
                .selectFrom(MEASUREMENT)
                .where(MEASUREMENT.LOCATION_NAME.eq(name))
                .and(MEASUREMENT.TIME_STAMP.gt(time))
                .fetch(this::measurementObject);
    }

    public List<IWeatherMeasurementDTO> getAllFromLocation(String name) {
        return dslContext
                .selectFrom(MEASUREMENT)
                .where(MEASUREMENT.LOCATION_NAME.eq(name))
                .fetch(this::measurementObject);
    }
}
