package com.verification.match.domain.iss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@Scope(value = "prototype")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    @JsonProperty("_links")
    private HashMap<String, String> links;//
    private String external_id;
    private ArrayList faces;
    private String first_name;
    private String last_name;
    private ArrayList lists;
    private String middle_name;
    private String notes;
}
