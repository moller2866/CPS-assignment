package dk.sdu.cps.backend.weatherdata.responseModel;

import java.time.LocalDateTime;

public class Timeseries {
    private Data data;
    private String time;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
