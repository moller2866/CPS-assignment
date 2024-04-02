package dk.sdu.cps.backend.external.weatherResponseModel;

import java.util.List;

public class Properties {
    private List<Timeseries> timeseries;

    public List<Timeseries> getTimeseries() {
        return timeseries;
    }

    public void setTimeseries(List<Timeseries> timeseries) {
        this.timeseries = timeseries;
    }
}
