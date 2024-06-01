import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Weather from "../components/Weather";
import TemperatureChart from "../components/TemperatureChart";
import PrecipitationChart from "../components/PrecipitationChart";
import Navigation from "../components/Navigation";
import { useEffect, useState } from "react";
export default function Primary() {
  const [selectedLocation, setSelectedLocation] = useState("Odense");

  useEffect(() => {
    function getLocation() {
      const location = localStorage.getItem("location");
      if (location) {
        setSelectedLocation(location);
      }
    }
    getLocation();
  }, [setSelectedLocation]);

  return (
    <Router>
      <Navigation
        location={selectedLocation}
        setLocation={setSelectedLocation}
      />
      <Routes>
        <Route path="/" element={<Weather location={selectedLocation} />} />
        <Route
          path="/temperature-chart"
          element={<TemperatureChart location={selectedLocation} />}
        />
        <Route
          path="/precipitation-chart"
          element={<PrecipitationChart location={selectedLocation} />}
        />
      </Routes>
    </Router>
  );
}
