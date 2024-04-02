-- liquibase formatted sql
-- changeset moller:1
CREATE TABLE weather (
    id BIGSERIAL PRIMARY KEY,
    time TIMESTAMP NOT NULL,
    air_temperature FLOAT NOT NULL,
    relative_humidity FLOAT NOT NULL,
    wind_speed FLOAT NOT NULL,
    wind_from_direction FLOAT NOT NULL,
    wind_speed_of_gust FLOAT NOT NULL
);