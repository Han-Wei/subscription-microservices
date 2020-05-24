package info.lab.retrieverservice.client;

import info.lab.retrieverservice.entity.IanaTimeZoneSubscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "time-client",url = "http://worldtimeapi.org")
public interface IanaTimeZoneClient {

    @GetMapping("/api/timezone/{area}/{location}")
    IanaTimeZoneSubscription retrieveAreaLocationTime(
            @PathVariable String area,
            @PathVariable String location);
}