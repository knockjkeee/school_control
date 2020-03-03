package com.verification.parseschedule.service;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ParseServices {

    @Value("${file.path}")
    private String path;

    @Value("${file.path.json}")
    private String json;

    @Value("${file.path.txt}")
    private String txt;

    private CreateDataBase createDataBase;
    private VerificationDataTime verificationDataTime;

    public ParseServices(CreateDataBase createDataBase, VerificationDataTime verificationDataTime) {
        this.createDataBase = createDataBase;
        this.verificationDataTime = verificationDataTime;
    }

    public void parse() throws IOException {
        System.out.println(path);
        File fileXml = new File(path);

        System.out.println(fileXml.length());
        InputStreamReader inputStream = new InputStreamReader(new FileInputStream(fileXml), "windows-1251");
        String xmlToString = IOUtils.toString(inputStream);

        System.out.println(xmlToString.length());

        JSONObject xmlJSONObj = XML.toJSONObject(xmlToString);
        String jsonPrettyPrintString = xmlJSONObj.toString();
        File tempFileJson = new File(json);
        File tempFileTxt = new File(txt);
        tempFileTxt.createNewFile();
        tempFileJson.createNewFile();

        createDataBase.createData(jsonPrettyPrintString, tempFileJson, tempFileTxt);
//        verificationDataTime.checkData();

        System.out.println("Parse file xml completed");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}





