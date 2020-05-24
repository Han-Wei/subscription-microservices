package info.lab.subscriptionservice.repository;

import info.lab.subscriptionservice.entity.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription,Long> {

}
