package info.lab.subscriptionservice.entity;

import lombok.Data;

@Data
public class ApodSubscription {
    private String date;
    private String explanation;
    private String url;
    private String title;
    private String responseBy;
}
