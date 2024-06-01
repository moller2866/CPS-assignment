import axios from "axios";
const BASE_URL = import.meta.env.VITE_API_URL;

export const fetchLocations = async () => {
  try {
    const locData = await axios.get(BASE_URL + "/location/all");
    return locData;
  } catch (error) {
    console.error("Failed to fetch location data:", error);
    throw error;
  }
};

export const addLocation = async (location) => {
  try {
    const response = await axios.post(BASE_URL + "/location/add", {
      name: location,
    });
    return response;
  } catch (error) {
    console.error("Failed to add location:");
  }
};

export const deleteLocation = async (location) => {
  try {
    const response = await axios.delete(BASE_URL + "/location/delete", {
      data: { name: location },
    });
    return response;
  } catch (error) {
    console.error("Failed to delete location:", error);
  }
};
