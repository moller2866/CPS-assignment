import React, { useState } from "react";
import { Button, Menu, MenuItem, TextField, Box } from "@mui/material";
import { useSnackbar } from "notistack";
import { addLocation } from "../services/LocationService";

function LocationInputMenu(params) {
  const { setLocation } = params;
  const [anchorEl, setAnchorEl] = useState(null);
  const { enqueueSnackbar } = useSnackbar();

  const [locationName, setLocationName] = useState("");

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const handleSubmit = async () => {
    try {
      const response = await addLocation(locationName);

      if (response.status === 200) {
        setLocation(locationName);
        enqueueSnackbar("City added", { variant: "success" });
      }
    } catch (error) {
      enqueueSnackbar("The city '" + locationName + "' Does not exist", {
        variant: "error",
      });
    }
    handleClose();
  };

  return (
    <Box sx={{ m: 2 }}>
      <Button variant="contained" onClick={handleClick}>
        Add City
      </Button>
      <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={handleClose}>
        <MenuItem>
          <Box component="form" display="flex" flexDirection="column">
            <TextField
              label="City Name"
              value={locationName}
              onChange={(e) => setLocationName(e.target.value)}
              margin="normal"
              autoFocus
            />
            <Button variant="contained" onClick={handleSubmit} sx={{ mt: 2 }}>
              Submit
            </Button>
          </Box>
        </MenuItem>
      </Menu>
    </Box>
  );
}

export default LocationInputMenu;
