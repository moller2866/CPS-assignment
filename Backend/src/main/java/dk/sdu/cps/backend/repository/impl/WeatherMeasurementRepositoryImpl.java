package dk.sdu.cps.backend.repository;

import org.jooq.DSLContext;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.jooq.generated.public_.tables.Weather.WEATHER;

@Repository
public class WeatherRepository {

    DSLContext dslContext;

    public WeatherRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public WeatherRecord getLatestWeatherData() {
        return dslContext.selectFrom(WEATHER)
                .orderBy(WEATHER.TIME.desc())
                .limit(1)
                .fetchOneInto(WeatherRecord.class);
    }

    public void create(WeatherRecord weatherRecord) {
        dslContext
                .insertInto(WEATHER,
                        WEATHER.TIME,
                        WEATHER.AIR_TEMPERATURE,
                        WEATHER.RELATIVE_HUMIDITY,
                        WEATHER.WIND_SPEED,
                        WEATHER.WIND_FROM_DIRECTION,
                        WEATHER.WIND_SPEED_OF_GUST)
                .values(
                        weatherRecord.getTime(),
                        weatherRecord.getAirTemperature(),
                        weatherRecord.getRelativeHumidity(),
                        weatherRecord.getWindSpeed(),
                        weatherRecord.getWindFromDirection(),
                        weatherRecord.getWindSpeedOfGust())
                .execute();
    }

    public List<WeatherRecord> getWeatherDataSince(LocalDateTime time) {
        return dslContext.selectFrom(WEATHER)
                .where(WEATHER.TIME.gt(time))
                .fetchInto(WeatherRecord.class);
    }

    public WeatherRecord getByTime(LocalDateTime time) {
        return dslContext.selectFrom(WEATHER)
                .where(WEATHER.TIME.eq(time))
                .fetchOneInto(WeatherRecord.class);
    }

    public List<WeatherRecord> getAll() {
        return dslContext.selectFrom(WEATHER)
                .fetchInto(WeatherRecord.class);
    }
}
