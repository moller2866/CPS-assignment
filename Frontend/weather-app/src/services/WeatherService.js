import axios from "axios";
const BASE_URL = import.meta.env.VITE_API_URL;

export const fetchWeatherData = async (location, unit = "celsius") => {
  try {
    const response = await axios.get(BASE_URL + "/weather/latest", {
      params: { location: location, unit: unit },
    });
    return response;
  } catch (error) {
    console.error("Failed to fetch weather data:", error);
    throw error;
  }
};

export const fetchAllWeatherData = async (location, unit = "celsius") => {
  try {
    const response = await axios.get(BASE_URL + "/weather/all", {
      params: { location: location, unit: unit },
    });
    return response;
  } catch (error) {
    console.error("Failed to fetch all weather data:", error);
    throw error;
  }
};
