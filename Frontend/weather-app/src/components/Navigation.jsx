import { useNavigate } from "react-router-dom";
import { Button, Box } from "@mui/material";
import LocationInputMenu from "./LocationInputMenu";
import SelectLocation from "./SelectLocation";

function Navigation(props) {
  const { location, setLocation } = props;
  const navigate = useNavigate();

  return (
    <Box
      sx={{
        bgcolor: "background.paper",
        display: "flex",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Button
        variant="contained"
        color="primary"
        onClick={() => navigate("/")}
        sx={{ m: 2 }}
      >
        Weather
      </Button>
      <Button
        variant="contained"
        color="secondary"
        sx={{ m: 2 }}
        onClick={() => navigate("/temperature-chart")}
      >
        Temperature Chart
      </Button>
      <Button
        variant="contained"
        color="secondary"
        sx={{ m: 2 }}
        onClick={() => navigate("/precipitation-chart")}
      >
        Precipitation Chart
      </Button>
      <SelectLocation location={location} setLocation={setLocation} />
      <LocationInputMenu location={location} setLocation={setLocation} />
    </Box>
  );
}

export default Navigation;
