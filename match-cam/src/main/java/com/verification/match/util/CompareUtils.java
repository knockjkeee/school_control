package com.verification.match.util;

import com.verification.match.domain.transit.Transit;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CompareUtils {

    public static String newCurrentTimeTransit(Transit transit) {
        String timestampTransit = transit.getEvtDateTime();
        String currentTimeTransit = timestampTransit.substring(0, timestampTransit.length() - 2);
        return currentTimeTransit.replaceAll(" ", "T");
    }

    public static String getDataFormatTransitYear(Transit transit, DateTimeFormatter formatterYears) {
        String newCurrentTimeTransit = newCurrentTimeTransit(transit);
        LocalDateTime dateTimeTransit = LocalDateTime.parse(newCurrentTimeTransit);
        return dateTimeTransit.format(formatterYears);
    }

    public static String getDataFormatTransitTime(Transit transit, DateTimeFormatter formatterTime) {

        String newCurrentTimeTransit = newCurrentTimeTransit(transit);
        LocalDateTime dateTimeTransit = LocalDateTime.parse(newCurrentTimeTransit);
        return dateTimeTransit.format(formatterTime);

    }


}
