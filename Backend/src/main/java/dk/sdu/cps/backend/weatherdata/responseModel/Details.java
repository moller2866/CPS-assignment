package dk.sdu.cps.backend.weatherdata.responseModel;

public class Details {
    private Double air_temperature;
    private Double relative_humidity;
    private Double wind_from_direction;
    private Double wind_speed;
    private Double wind_speed_of_gust;

    public Double getAir_temperature() {
        return air_temperature;
    }

    public void setAir_temperature(Double air_temperature) {
        this.air_temperature = air_temperature;
    }

    public Double getRelative_humidity() {
        return relative_humidity;
    }

    public void setRelative_humidity(Double relative_humidity) {
        this.relative_humidity = relative_humidity;
    }

    public Double getWind_from_direction() {
        return wind_from_direction;
    }

    public void setWind_from_direction(Double wind_from_direction) {
        this.wind_from_direction = wind_from_direction;
    }

    public Double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(Double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public Double getWind_speed_of_gust() {
        return wind_speed_of_gust;
    }

    public void setWind_speed_of_gust(Double wind_speed_of_gust) {
        this.wind_speed_of_gust = wind_speed_of_gust;
    }
}
