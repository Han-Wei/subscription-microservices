package info.lab.retrieverservice.client;

import info.lab.retrieverservice.entity.ApodSubscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="apod-client",url = "https://api.nasa.gov")
public interface ApodClient {
    @GetMapping("/planetary/apod?api_key={apod_api_key}")
    ApodSubscription retrieveApod(@PathVariable String apod_api_key);
}
