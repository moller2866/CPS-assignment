import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Weather from "../components/Weather";
import TemperatureChart from "../components/TemperatureChart";
import PrecipitationChart from "../components/PrecipitationChart";
import Navigation from "../components/Navigation";
import { useEffect, useState } from "react";
export default function Primary() {
  const [selectedLocation, setSelectedLocation] = useState("Odense");
  const [tempUnit, setTempUnit] = useState("°C");
  const [unitValue, setUnitValue] = useState("celsius");

  useEffect(() => {
    function getLocation() {
      const location = localStorage.getItem("location");
      if (location) {
        setSelectedLocation(location);
      }
    }
    getLocation();
  }, [setSelectedLocation]);

  useEffect(() => {
    function getUnit() {
      if (unitValue === "fahrenheit") {
        setTempUnit("°F");
      } else {
        setTempUnit("°C");
      }
    }
    console.log(unitValue);
    getUnit();
  }, [unitValue]);

  return (
    <Router>
      <Navigation
        location={selectedLocation}
        setLocation={setSelectedLocation}
        unitValue={unitValue}
        setUnitValue={setUnitValue}
      />
      <Routes>
        <Route
          path="/"
          element={
            <Weather
              location={selectedLocation}
              tempUnit={tempUnit}
              unitValue={unitValue}
            />
          }
        />
        <Route
          path="/temperature-chart"
          element={
            <TemperatureChart
              location={selectedLocation}
              tempUnit={tempUnit}
              unitValue={unitValue}
            />
          }
        />
        <Route
          path="/precipitation-chart"
          element={<PrecipitationChart location={selectedLocation} />}
        />
      </Routes>
    </Router>
  );
}
