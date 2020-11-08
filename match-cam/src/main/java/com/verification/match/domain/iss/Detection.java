package com.verification.match.domain.iss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

@Data
@Scope(value ="prototype")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Detection {
    @JsonProperty("_links")
    private HashMap<String, String> links;
    private HashMap<String, String> attributes;
    private HashMap<String, String> bounding_box;
    private String feed;
    @JsonProperty("track_finish_timestamp")
    private String trackFinishTimestamp;

}
