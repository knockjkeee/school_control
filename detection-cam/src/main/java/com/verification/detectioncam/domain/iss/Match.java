package com.verification.detectioncam.domain.iss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

@Data
@Scope(value = "prototype")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {
    @JsonProperty("_links")
    private HashMap<String, String> links;
    private HashMap<String, String> bounding_box;
    private Detection detection;
    private String feed;
    private String id; //
    private HashMap<String, String> liveness; //
    @JsonProperty("matched_person_face_image")
    private MatchPersonalImage matchedPersonFaceImage;
    private Person person;
    private String person_id;
    private String similarity;
    private String timestamp;
}
