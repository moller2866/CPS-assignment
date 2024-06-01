import React from "react";
import { LineChart } from "@mui/x-charts/LineChart";
import { useEffect, useState } from "react";
import { fetchAllWeatherData } from "../services/WeatherService";
import { useSnackbar } from "notistack";
import { Box } from "@mui/material";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";

export default function BasicLineChart(props) {
  const { location } = props;
  const [weatherData, setWeatherData] = useState([]);
  const { enqueueSnackbar } = useSnackbar();
  const [tempUnit, setTempUnit] = useState("°C");
  const [unitValue, setUnitValue] = useState("celsius");

  useEffect(() => {
    const fetchWeatherData = async () => {
      try {
        const response = await fetchAllWeatherData(location, unitValue);
        setWeatherData(response.data);
        if (unitValue === "fahrenheit") {
          setTempUnit("°F");
        } else {
          setTempUnit("°C");
        }
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

  const handleChange = (event) => {
    setUnitValue(event.target.value);
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
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
              return formattedDate; // Add return statement here
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
