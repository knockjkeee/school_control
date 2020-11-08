package com.verification.viewverification.util;

import com.helger.commons.csv.CSVWriter;
import com.verification.viewverification.domain.Result;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

@Component
public class CreateCSV {

    public static String getDataCSV(List<Result> data) throws IOException {
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        csvWriter.writeNext("№ Турникета", "ID СКУД", "ФИО",
                "Дата прохода", "Время прохода", "ID ISS",
                "ФИО ISS", "Дата верификации",
                "Время верификации", "Сравнивание");
        data.forEach(temp -> csvWriter.writeNext("" +
                        temp.getTurnstileAddr(), String.valueOf(temp.getIdTransit()),
                temp.getUsernameTransit(), temp.getDataTransit(), temp.getTimeTransit()
                , String.valueOf(temp.getIdVerification()), temp.getUsernameVerification()
                , temp.getDataVerification(), temp.getTimeVerification(), temp.getResult()));

        return IOUtils.toString(IOUtils.toInputStream(stringWriter.toString(), "windows-1251"), "windows-1251");
    }


}
