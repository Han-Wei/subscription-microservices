package info.lab.subscriptionservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Subscription {

    private long id;
    private SubscriptionType type;
    private String area;
    private String location;
}
