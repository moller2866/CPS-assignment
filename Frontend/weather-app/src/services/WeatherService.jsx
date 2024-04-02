import axios from 'axios';
const BASE_URL = import.meta.env.VITE_API_URL;

export const fetchWeatherData = async () => {
  try {
    const response = await axios.get(BASE_URL);
    return response.data;
  } catch (error) {
    console.error("Failed to fetch weather data:", error);
    throw error;
    }
};

// Function to fetch all weather data
export const fetchAllWeatherData = async () => {
    try {
      const response = await axios.get(BASE_URL + '/all');
      return response.data;
    } catch (error) {
      console.error("Failed to fetch all weather data:", error);
      throw error;
    }
  };

  