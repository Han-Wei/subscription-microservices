package info.lab.subscriptionservice.controller;

import info.lab.subscriptionservice.client.RetrieverClient;
import info.lab.subscriptionservice.entity.Subscription;
import info.lab.subscriptionservice.entity.SubscriptionType;
import info.lab.subscriptionservice.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "subscriptions")
public class SubscriptionController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    RetrieverClient retrieverClient;

    @GetMapping("/{id}")
    @ResponseBody
    public Object retrieveSubscription(@PathVariable Long id ){

        logger.info("Trying to retrieving subscription id : {}",id);

        Optional<Subscription> subscription = subscriptionService.findById(id);

        logger.info("Parsing Subscription : {}",subscription);

        if(subscription.isPresent()){
            logger.info("Content : {}",subscription.get());
            if(subscription.get().getType().equals(SubscriptionType.APOD)){
                logger.info("Trying to get APOD Subscription");
                return retrieverClient.retrieveApodSubscription();
            }
            if(subscription.get().getType().equals(SubscriptionType.IANA_TIME_ZONE)){
                logger.info("Trying to get IANA TIME ZONE Subscription");
                return retrieverClient.retrieveIanaTimeZoneSubscription(
                        subscription.get().getArea(),
                        subscription.get().getLocation());
            }

        }
        return null;
    }
}
