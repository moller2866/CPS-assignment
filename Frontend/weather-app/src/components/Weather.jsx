import React, { useEffect, useState } from "react";
import { fetchWeatherData } from "../services/WeatherService";
import {
  Card,
  CardContent,
  Typography,
  Grid,
  CircularProgress,
  Box,
} from "@mui/material";
import { useSnackbar } from "notistack";

function Weather(props) {
  const [weather, setWeather] = useState(null);
  const { enqueueSnackbar } = useSnackbar();
  const { location, unitValue, tempUnit } = props;

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetchWeatherData(location, unitValue);
        setWeather(response.data);
      } catch (error) {
        enqueueSnackbar("Failed to fetch weather data", { variant: "error" });
      }
    };
    fetchData();
    const interval = setInterval(() => {
      fetchData();
    }, 5 * 60 * 1000);

    return () => clearInterval(interval);
  }, [location, unitValue]);

  if (!weather) {
    return (
      <Box
        sx={{
          display: "flex",
          flexDirection: "row",
          justifyContent: "center",
          alignItems: "center",
          mt: 5,
        }}
      >
        <CircularProgress size={"100px"} />
      </Box>
    );
  }
  return (
    <Box>
      <Card sx={{ maxWidth: 345, margin: "auto", mt: 5 }}>
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {location}
          </Typography>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <img
                src={`./weatherSym/${weather.nextHourSym}.png`}
                alt="Weather Symbol"
              />
            </Grid>
            <Grid item xs={12}>
              <Typography variant="body2" color="text.secondary">
                Time: {weather.timeStamp}
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="body2" color="text.secondary">
                Temperature: {weather.temperature}
                {tempUnit}
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="body2" color="text.secondary">
                Humidity: {weather.humidity}%
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="body2" color="text.secondary">
                Wind Speed: {weather.windSpeed}m/s
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="body2" color="text.secondary">
                Wind Direction: {weather.windDirection}°
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="body2" color="text.secondary">
                Speed of Gust: {weather.windOfGust}m/s
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="body2" color="text.secondary">
                Precipitation: {weather.precipitationAmount}mm
              </Typography>
            </Grid>
          </Grid>
        </CardContent>
      </Card>
    </Box>
  );
}

export default Weather;
