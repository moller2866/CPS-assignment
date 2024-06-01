package dk.sdu.cps.backend.service;

import dk.sdu.cps.backend.dto.LocationDTO;
import dk.sdu.cps.backend.exceptions.LocationNotFoundException;
import dk.sdu.cps.backend.external.METExternalAPI;
import dk.sdu.cps.backend.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

class LocationServiceTest {

    private LocationRepository locationRepository;
    private METExternalAPI METExternalAPI;
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        locationRepository = mock(LocationRepository.class);
        METExternalAPI = mock(METExternalAPI.class);
        locationService = new LocationService(locationRepository, METExternalAPI);
    }

    @Test
    void getAllLocations() {
        LocationDTO locationDTO = new LocationDTO();
        when(locationRepository.getAll()).thenReturn(Collections.singletonList(locationDTO));

        List<LocationDTO> result = locationService.getAllLocations();

        assertEquals(1, result.size());
        assertEquals(locationDTO, result.get(0));
    }

    @Test
    void getLocation() {
        String locationName = "TestLocation";
        LocationDTO locationDTO = new LocationDTO();
        when(locationRepository.get(locationName)).thenReturn(locationDTO);

        LocationDTO result = locationService.getLocation(locationName);

        assertEquals(locationDTO, result);
    }

    @Test
    void addLocation() {
        doNothing().when(locationRepository).create(any(LocationDTO.class));
        doNothing().when(METExternalAPI).FetchAndSaveMeasurement(any(LocationDTO.class));
        assertDoesNotThrow(() -> locationService.addLocation("Skive"));
        assertThrows(LocationNotFoundException.class, () -> locationService.addLocation("notExists"));
    }

    @Test
    void deleteLocation() {
        String locationName = "TestLocation";
        doNothing().when(locationRepository).delete(locationName);

        assertDoesNotThrow(() -> locationService.deleteLocation(locationName));
    }

}