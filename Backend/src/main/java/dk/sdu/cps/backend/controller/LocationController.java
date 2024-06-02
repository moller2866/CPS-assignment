package dk.sdu.cps.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dk.sdu.cps.backend.dto.LocationDTO;
import dk.sdu.cps.backend.exceptions.LocationNotFoundException;
import dk.sdu.cps.backend.service.LocationService;

@RestController
@RequestMapping("/api/location")
@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {

    LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<String> handleLocationNotFoundException(LocationNotFoundException e) {
        return new ResponseEntity<>("Location not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public List<LocationDTO> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/{name}")
    public LocationDTO getLocation(@PathVariable String name) {
        return locationService.getLocation(name);
    }

    @PostMapping("/add")
    public void addLocation(@RequestBody LocationDTO LocationDTO) {
        locationService.addLocation(LocationDTO.getName());
    }

    @DeleteMapping("/delete")
    public void deleteLocation(@RequestBody LocationDTO LocationDTO) {
        locationService.deleteLocation(LocationDTO.getName());
    }
}
