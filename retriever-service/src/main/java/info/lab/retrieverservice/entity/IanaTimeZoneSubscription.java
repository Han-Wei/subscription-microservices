package info.lab.retrieverservice.entity;

import lombok.Data;

@Data
public class IanaTimeZoneSubscription {
    private String datetime;
    private String utc_offset;
    private String timezone;
    private String responseBy;
}
