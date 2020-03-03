package com.verification.match.domain.iss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;

@Data
@Scope(value = "prototype")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchPersonalImage {
    @JsonProperty("_links")
    private HashMap<String, String> links;//
    private String id;
}
