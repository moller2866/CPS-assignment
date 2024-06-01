import React, { useEffect, useState } from "react";
import { deleteLocation, fetchLocations } from "../services/LocationService";
import { MenuItem, Select, IconButton } from "@mui/material";
import { useSnackbar } from "notistack";
import DeleteIcon from "@mui/icons-material/Delete";

function SelectLocation(props) {
  const { location, setLocation } = props;
  const { enqueueSnackbar } = useSnackbar();
  const [locations, setLocations] = useState([]);

  useEffect(() => {
    const getLocations = async () => {
      try {
        const response = await fetchLocations();
        const data = response.data;
        setLocations(data);
      } catch (error) {}
    };
    getLocations();
  }, [location]);

  const handleLocationChange = (event) => {
    setLocation(event.target.value);
  };

  const handleDeleteLocation = (locationName) => {
    const confirmDelete = window.confirm(
      "All data for this city will be lost. Are you sure you want to continue?"
    );
    if (confirmDelete) {
      deleteLocation(locationName);
      setLocations(locations.filter((loc) => loc.name !== locationName));
      setLocation(locations[0].name);
      enqueueSnackbar("Location deleted", { variant: "success" });
    }
  };

  return (
    <>
      <Select
        value={location}
        onChange={handleLocationChange}
        sx={{
          m: 2,
          justifyContent: "space-between",
          bgcolor: "background.default",
        }}
        renderValue={(selected) => selected}
      >
        {locations.map((loc, idx) => (
          <MenuItem
            key={idx}
            value={loc.name}
            sx={{
              display: "flex",
              justifyContent: "space-between",
              bgcolor: "background.default",
            }}
          >
            {loc.name}
            <IconButton
              edge="end"
              aria-label="delete"
              onClick={() => handleDeleteLocation(loc.name)}
              sx={{ marginLeft: "auto" }}
            >
              <DeleteIcon />
            </IconButton>
          </MenuItem>
        ))}
      </Select>
    </>
  );
}

export default SelectLocation;
