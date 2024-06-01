package dk.sdu.cps.backend.repository;

import dk.sdu.cps.backend.dto.LocationDTO;

import java.util.List;

public interface LocationRepository {
    void create(LocationDTO location);

    LocationDTO get(String name);

    List<LocationDTO> getAll();

    void delete(String name);
}
