import * as React from 'react';
import { LineChart } from '@mui/x-charts/LineChart';
import { useEffect, useState } from 'react';
import { fetchAllWeatherData } from '../services/WeatherService';
import { useSnackbar } from 'notistack';

export default function BasicLineChart() {
  const [weatherData, setWeatherData] = useState([]);
  const { enqueueSnackbar } = useSnackbar();
  useEffect(() => {
    const fetchWeatherData = async () => {
      try {
        const data = await fetchAllWeatherData();
        setWeatherData(data);
      } catch (error) {
        console.error("Error fetching weather data:", error);
        enqueueSnackbar("Failed to fetch weather data", { variant: 'error' });
      }
    };

    fetchWeatherData();
  }, []);

  return (
    <LineChart
      xAxis={[
        {
          id: "Timestamp",
          data: weatherData.map((data) => Date.parse(data.time)),
          valueFormatter: (value) => {
            const date = new Date(value);
            const formattedDate =
              date.getFullYear() +
              "-" +
              String(date.getMonth() + 1).padStart(2, "0") +
              "-" +
              String(date.getDate()).padStart(2, "0") +
              "T" +
              String(date.getHours()).padStart(2, "0") +
              ":" +
              String(date.getMinutes()).padStart(2, "0") +
              ":" +
              String(date.getSeconds()).padStart(2, "0");
            return formattedDate; // Add return statement here
          },
        },
      ]}
      series={[
        {
          id: "Temperature",
          data: weatherData.map((data) => data.airTemperature),
          valueFormatter: (value) => `${value}°C`,
        },
      ]}
      width={window.innerWidth > 1000 ? 1000 : window.innerWidth}
      height={window.innerHeight > 500 ? 500 : window.innerHeight}
    />
  );
}