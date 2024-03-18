package dk.sdu.cps.backend.weatherdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
    Optional<WeatherData> findByTime(LocalDateTime time);
}