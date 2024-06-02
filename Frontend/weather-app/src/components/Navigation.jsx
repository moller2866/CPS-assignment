import { useNavigate } from "react-router-dom";
import { Button, Box } from "@mui/material";
import LocationInputMenu from "./LocationInputMenu";
import SelectLocation from "./SelectLocation";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";

function Navigation(props) {
  const { location, setLocation, unitValue, setUnitValue } = props;
  const navigate = useNavigate();

  const handleChange = (event) => {
    setUnitValue(event.target.value);
  };

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
      <FormControl sx={{ mt: 2 }}>
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
