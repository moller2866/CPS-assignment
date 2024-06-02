import * as React from "react";
import { LineChart } from "@mui/x-charts/LineChart";
import { useEffect, useState } from "react";
import { fetchAllWeatherData } from "../services/WeatherService";
import { useSnackbar } from "notistack";
import { Box } from "@mui/material";

export default function BasicLineChart(props) {
  const { location } = props;
  const [weatherData, setWeatherData] = useState([]);
  const { enqueueSnackbar } = useSnackbar();

  useEffect(() => {
    const fetchWeatherData = async () => {
      try {
        const response = await fetchAllWeatherData(location);
        setWeatherData(response.data);
      } catch (error) {
        enqueueSnackbar("Failed to fetch weather data", { variant: "error" });
      }
    };

    fetchWeatherData();

    const interval = setInterval(() => {
      fetchData();
    }, 5 * 60 * 1000);

    return () => clearInterval(interval);
  }, [location]);

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <LineChart
        xAxis={[
          {
            id: "Timestamp",
            data: weatherData.map((data) => Date.parse(data.timeStamp)),
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
              return formattedDate;
            },
          },
        ]}
        series={[
          {
            id: "Precipitation",
            data: weatherData.map((data) => data.precipitationAmount),
            valueFormatter: (value) => `${value}mm`,
          },
        ]}
        width={window.innerWidth > 1000 ? 1000 : window.innerWidth}
        height={window.innerHeight > 500 ? 500 : window.innerHeight}
      />
    </Box>
  );
}
