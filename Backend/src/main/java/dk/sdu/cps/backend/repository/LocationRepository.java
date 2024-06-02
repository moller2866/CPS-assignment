package dk.sdu.cps.backend.repository;

import java.util.List;

import dk.sdu.cps.backend.dto.LocationDTO;

public interface LocationRepository {
    void create(LocationDTO location);

    LocationDTO get(String name);

    List<LocationDTO> getAll();

    void delete(String name);
}
