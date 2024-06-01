// components/Weather.js
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
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";
import { useSnackbar } from "notistack";

function Weather(props) {
  const [weather, setWeather] = useState(null);
  const { enqueueSnackbar } = useSnackbar();
  const { location } = props;
  const [tempUnit, setTempUnit] = useState("°C");
  const [unitValue, setUnitValue] = useState("celsius");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetchWeatherData(location, unitValue);
        setWeather(response.data);
        if (unitValue === "fahrenheit") {
          setTempUnit("°F");
        } else {
          setTempUnit("°C");
        }
      } catch (error) {
        // Display a snackbar notification
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
      <Box justifyContent={"center"} alignContent={"center"}>
        <CircularProgress />;
      </Box>
    );
  }

  const handleChange = (event) => {
    setUnitValue(event.target.value);
  };

  return (
    <Box>
      <FormControl>
        <FormLabel>Unit</FormLabel>
        <RadioGroup value={unitValue} onChange={handleChange}>
          <FormControlLabel
            value="celsius"
            control={<Radio />}
            label="Celsius"
          />
          <FormControlLabel
            value="fahrenheit"
            control={<Radio />}
            label="Fahrenheit"
          />
        </RadioGroup>
      </FormControl>
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
              <Typography variant="body2" color="text.secondary" sx={{ ml: 1 }}>
                Air Temperature: {weather.temperature}
                {tempUnit}
              </Typography>
            </Grid>
            <Grid item xs={12}>
              <Typography variant="body2" color="text.secondary">
                Relative Humidity: {weather.humidity}%
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
