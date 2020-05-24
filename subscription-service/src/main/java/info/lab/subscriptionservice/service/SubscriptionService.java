package info.lab.subscriptionservice.service;

import info.lab.subscriptionservice.entity.Subscription;
import info.lab.subscriptionservice.entity.SubscriptionType;
import info.lab.subscriptionservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;


    @PostConstruct
    private void createSimpleSubscriptions(){

        subscriptionRepository.save(Subscription.builder()
                .id(101)
                .type(SubscriptionType.APOD)
                .build());

        subscriptionRepository.save(Subscription.builder()
                .id(102)
                .type(SubscriptionType.IANA_TIME_ZONE)
                .area("Asia")
                .location("Taipei")
                .build());

    }

    public Optional<Subscription> findById(long id){
        return this.subscriptionRepository.findById(id);
    }

    public List<Subscription> findAll(){

        return this.subscriptionRepository.findAll();
    }

}
