package info.lab.retrieverservice.temp;

import lombok.Data;

import java.util.List;

@Data
public class CityWeatherSubscription {
    private String dt; // epoch time-string with GMT
    private String name; // city name
    private String description; // weather description.
    //private List<Weather> weather; // weather

    /*
    @Data
    public class Weather{
        private String main;
        private String description;
    }
    */
}
