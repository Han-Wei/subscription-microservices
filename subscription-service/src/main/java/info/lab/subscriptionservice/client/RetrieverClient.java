package info.lab.subscriptionservice.client;

import info.lab.subscriptionservice.entity.ApodSubscription;
import info.lab.subscriptionservice.entity.IanaTimeZoneSubscription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "retriever-service")
public interface RetrieverClient {

    @GetMapping("/retrievers/time/{area}/{location}")
    public IanaTimeZoneSubscription retrieveIanaTimeZoneSubscription(
            @PathVariable String area,
            @PathVariable String location);

    @GetMapping("/retrievers/apod")
    public ApodSubscription retrieveApodSubscription();

}
