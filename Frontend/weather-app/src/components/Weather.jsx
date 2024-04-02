// components/Weather.js
import React, { useEffect, useState } from 'react';
import { fetchWeatherData } from '../services/WeatherService';
import { Card, CardContent, Typography, Grid, CircularProgress } from '@mui/material';
import { useSnackbar } from 'notistack';

const Weather = () => {
  const [weather, setWeather] = useState(null);
  const { enqueueSnackbar } = useSnackbar();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchWeatherData();
        setWeather(data);
      } catch (error) {
        console.error("Error fetching weather data:", error);

        // Display a snackbar notification
        enqueueSnackbar("Failed to fetch weather data", { variant: 'error' });
      }
    };

    fetchData();
  }, []);

  if (!weather) {
    return <CircularProgress />;
  }

  return (
    <Card sx={{ maxWidth: 345, margin: 'auto', mt: 5 }}>
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          Current Weather
        </Typography>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <Typography variant="body2" color="text.secondary">
              Time: {weather.time}
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography variant="body2" color="text.secondary" sx={{ ml: 1 }}>
              Air Temperature: {weather.airTemperature}°C
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography variant="body2" color="text.secondary">
              Relative Humidity: {weather.relativeHumidity}%
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography variant="body2" color="text.secondary">
              Wind Speed: {weather.windSpeed} km/h
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography variant="body2" color="text.secondary">
              Wind Direction: {weather.windFromDirection} degrees
            </Typography>
          </Grid>
          <Grid item xs={12}>
            <Typography variant="body2" color="text.secondary">
              Speed of Gust: {weather.windSpeedOfGust} km/h
            </Typography>
          </Grid>
        </Grid>
      </CardContent>
    </Card>
  );
};

export default Weather;
