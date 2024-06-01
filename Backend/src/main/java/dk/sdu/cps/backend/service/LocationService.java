package dk.sdu.cps.backend.service;

import dk.sdu.cps.backend.dto.LocationDTO;
import dk.sdu.cps.backend.exceptions.LocationNotFoundException;
import dk.sdu.cps.backend.external.METExternalAPI;
import dk.sdu.cps.backend.repository.LocationRepository;
import dk.sdu.cps.backend.util.Coordinates;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LocationService {

    LocationRepository locationRepository;
    METExternalAPI METExternalAPI;

    public LocationService(LocationRepository locationRepository, METExternalAPI METExternalAPI) {
        this.locationRepository = locationRepository;
        this.METExternalAPI = METExternalAPI;
    }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.getAll();
    }

    public LocationDTO getLocation(String name) {
        return locationRepository.get(name);
    }

    public void addLocation(String name) {
        Coordinates coordinates = new Coordinates();
        Map<String, Float> map = coordinates.readCSVFile(name);
        if (map.isEmpty()) {
            throw new LocationNotFoundException("Location not found");
        }
        LocationDTO location = new LocationDTO();
        location.setName(name);
        location.setLatitude(map.get("Latitude"));
        location.setLongitude(map.get("Longitude"));
        locationRepository.create(location);
        METExternalAPI.FetchAndSaveMeasurement(location);
    }

    public void deleteLocation(String name) {
        locationRepository.delete(name);
    }


}
