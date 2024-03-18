-- liquibase formatted sql
-- changeset moller:1
CREATE TABLE weather_data (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    time TIMESTAMP NOT NULL,
    air_temperature FLOAT NOT NULL,
    relative_humidity FLOAT NOT NULL,
    wind_speed FLOAT NOT NULL,
    wind_from_direction FLOAT NOT NULL,
    wind_speed_of_gust FLOAT NOT NULL
);

-- changeset moller:2
ALTER TABLE weather_data
    ADD COLUMN city VARCHAR(255) NOT NULL default 'Odense';