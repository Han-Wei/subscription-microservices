package info.lab.retrieverservice.controller;

import info.lab.retrieverservice.client.ApodClient;
import info.lab.retrieverservice.client.IanaTimeZoneClient;
import info.lab.retrieverservice.entity.ApodSubscription;
import info.lab.retrieverservice.entity.IanaTimeZoneSubscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "retrievers")
public class RetrieverController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${apod_api_key}")
    String apod_api_key;

    @Autowired
    private IanaTimeZoneClient ianaTimeZoneClient;

    @Autowired
    private ApodClient apodClient;

    @Autowired
    private Environment environment;

    @GetMapping("/time/{area}/{location}")
    @ResponseBody
    public IanaTimeZoneSubscription retrieveAreaLocationTime(@PathVariable String area,
                                                             @PathVariable String location){

        logger.info("Retrieving time for {}/{}",area,location);
        IanaTimeZoneSubscription subscription = ianaTimeZoneClient.retrieveAreaLocationTime(area,location);
        subscription.setResponseBy(environment.getProperty("local.server.port"));
        return subscription;
    }

    @GetMapping("/apod")
    @ResponseBody
    public ApodSubscription retrieveApod(){
        logger.info("APOD Retriever is here for your service");
        logger.info("Retrieving Astronomy Picture of the Day, with KEY : {}",this.apod_api_key);
        ApodSubscription subscription = apodClient.retrieveApod(this.apod_api_key);
        subscription.setResponseBy(environment.getProperty("local.server.port"));
        return subscription;
    }
}
