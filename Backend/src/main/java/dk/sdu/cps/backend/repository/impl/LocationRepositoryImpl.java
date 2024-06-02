package dk.sdu.cps.backend.repository.impl;

import java.util.List;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import dk.sdu.cps.backend.dto.LocationDTO;
import dk.sdu.cps.backend.repository.LocationRepository;

import static org.jooq.generated.public_.tables.Location.LOCATION;

@Repository
public class LocationRepositoryImpl implements LocationRepository {
    DSLContext dslContext;

    public LocationRepositoryImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public void create(LocationDTO location) {

        if (dslContext.selectFrom(LOCATION).where(LOCATION.NAME.eq(location.getName())).fetchOne()
                != null) {
            return;
        }

        dslContext
                .insertInto(LOCATION, LOCATION.NAME, LOCATION.LATITUDE, LOCATION.LONGITUDE)
                .values(location.getName(), location.getLatitude(), location.getLongitude())
                .execute();
    }

    @Override
    public LocationDTO get(String name) {
        return dslContext
                .selectFrom(LOCATION)
                .where(LOCATION.NAME.eq(name))
                .fetchOneInto(LocationDTO.class);
    }

    @Override
    public List<LocationDTO> getAll() {
        return dslContext.selectFrom(LOCATION).fetchInto(LocationDTO.class);
    }

    @Override
    public void delete(String name) {
        dslContext.deleteFrom(LOCATION).where(LOCATION.NAME.eq(name)).execute();
    }
}
