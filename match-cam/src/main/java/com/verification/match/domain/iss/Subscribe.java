package com.verification.match.domain.iss;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

@Data
@Scope(value = "prototype")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscribe {
    private String action;
    private ArrayList feeds;
    private Match match;
    private Detection detection;
    private String plane;
    private String status;
}



