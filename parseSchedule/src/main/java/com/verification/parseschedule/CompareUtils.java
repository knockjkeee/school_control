package com.verification.parseschedule;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CompareUtils {
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
    private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

    public static String newCurrentTimeTransit(String transit) {
//        String timestampTransit = transit.getEvtDateTime();
        String currentTimeTransit = transit.substring(0, transit.length() - 2);
        return currentTimeTransit.replaceAll(" ", "T");
    }

    public static String getDataFormatTransitYear(String transit, DateTimeFormatter formatterYears) {
        String newCurrentTimeTransit = newCurrentTimeTransit(transit);
        LocalDateTime dateTimeTransit = LocalDateTime.parse(newCurrentTimeTransit);
        return dateTimeTransit.format(formatterYears);
    }

    public static String getDataFormatTransitTime(String transit, DateTimeFormatter formatterTime) {

        String newCurrentTimeTransit = newCurrentTimeTransit(transit);
        LocalDateTime dateTimeTransit = LocalDateTime.parse(newCurrentTimeTransit);
        return dateTimeTransit.format(formatterTime);

    }


    public static boolean equalsTimeData(String transitTime, Date currentTime) throws ParseException {
        String currentFormatTime = formatter.format(currentTime);//todo not avaible
        String formatTransitTime = getDataFormatTransitTime(transitTime, formatterTime);
        if (formatter.parse(currentFormatTime).getTime() > formatter.parse(formatTransitTime).getTime()) {
            return true;
        } else {
            return false;
        }
    }


    public static String getPeriodEndTime(String transitTime) {
        String formatTransitTime = getDataFormatTransitTime(transitTime, formatterTime);
        Map<Integer, String> map = Stream.of(new Object[][]{
                {1, "8:50"},
                {2, "9:40"},
                {3, "10:30"},
                {4, "11:20"},
                {5, "12:10"},
                {6, "13:20"},
                {7, "14:10"},
                {8, "15:00"},
        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
        String result = getEndtime(verificationPeriod(map, formatTransitTime));
        return result;
    }


    private static String verificationPeriod(Map<Integer, String> map, String currentTime) {
        List<Integer> current = splitStringTime(currentTime);
//        String val = null;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String value = entry.getValue();
            List<Integer> listValue = splitStringTime(value);
//            System.out.println(entry.getKey());
            if (current.get(0) == listValue.get(0)) {
                if (entry.getKey() == 8) {
//                    System.out.println(8);

                    if (current.get(1) >= 40) {
//                        System.out.println("null");
                        return "null";
                    }
                    return value;
                }
                return value;
            }
        }
        return null;
    }


    private static String getEndtime(String startTime) {
        String result = null;
        switch (startTime) {
            case ("8:50"):
                result = "9:30";
                break;
            case ("9:40"):
                result = "10:20";
                break;
            case ("10:30"):
                result = "11:10";
                break;
            case ("11:20"):
                result = "12:00";
                break;
            case ("12:10"):
                result = "12:50";
                break;
            case ("13:20"):
                result = "14:00";
                break;
            case ("14:10"):
                result = "14:50";
                break;
            case ("15:00"):
                result = "15:40";
                break;
            default:
                result = null;
        }

        return result;
    }

    private static List<Integer> splitStringTime(String time) {
        List<String> split = Arrays.asList(time.split(":"));
        List<Integer> result = new ArrayList<>();
        result.add(Integer.valueOf(split.get(0)));
        result.add(Integer.valueOf(split.get(1)));
        return result;
    }



}
