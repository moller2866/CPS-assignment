import React from "react";
import { LineChart } from "@mui/x-charts/LineChart";
import { useEffect, useState } from "react";
import { fetchAllWeatherData } from "../services/WeatherService";
import { useSnackbar } from "notistack";
import { Box } from "@mui/material";
import CircularProgress from "@mui/material/CircularProgress";

export default function BasicLineChart(props) {
  const { location, unitValue, tempUnit } = props;
  const [weatherData, setWeatherData] = useState([]);
  const { enqueueSnackbar } = useSnackbar();

  useEffect(() => {
    const fetchWeatherData = async () => {
      try {
        const response = await fetchAllWeatherData(location, unitValue);
        setWeatherData(response.data);
      } catch (error) {
        enqueueSnackbar("Failed to fetch all weather data", {
          variant: "error",
        });
      }
    };
    fetchWeatherData();
    const interval = setInterval(() => {
      fetchData();
    }, 5 * 60 * 1000);

    return () => clearInterval(interval);
  }, [location, unitValue]);

  if (weatherData.length === 0) {
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
            id: "Temperature",
            data: weatherData.map((data) => data.temperature),
            valueFormatter: (value) => `${value}${tempUnit}`,
          },
        ]}
        width={window.innerWidth > 1000 ? 1000 : window.innerWidth}
        height={window.innerHeight > 500 ? 500 : window.innerHeight}
      />
    </Box>
  );
}
