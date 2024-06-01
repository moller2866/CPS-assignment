package dk.sdu.cps.backend.external.weatherResponseModel;

import java.util.List;

public record Properties(List<Timeseries> timeseries) {}
