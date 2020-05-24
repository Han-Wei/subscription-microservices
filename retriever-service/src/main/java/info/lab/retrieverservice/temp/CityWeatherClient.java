package info.lab.retrieverservice.temp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;


@FeignClient(name = "weather-client",url = "https://api.openweathermap.org")
public interface CityWeatherClient {
    @GetMapping("/data/2.5/weather?units=metric&q={city}&APPID={city_weather_api_key}")
    Map<String, Object> retrieveCityWeather(@PathVariable String city, @PathVariable String city_weather_api_key);
}
