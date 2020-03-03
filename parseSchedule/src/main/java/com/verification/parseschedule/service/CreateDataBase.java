package com.verification.parseschedule.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verification.parseschedule.domain.*;
import com.verification.parseschedule.repo.TimetableRepo;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class CreateDataBase {
    private TimetableRepo timetableRepo;

    public CreateDataBase(TimetableRepo timetableRepo) {
        this.timetableRepo = timetableRepo;
    }

    public void createData(String jsonPrettyPrintString, File tempFile, File out) throws IOException {
        FileWriter fileWriter = new FileWriter(tempFile);
        FileWriter outWriter = new FileWriter(out);
        fileWriter.write(jsonPrettyPrintString);
        fileWriter.flush();
        fileWriter.close();

        XWPFDocument document = new XWPFDocument();

        ObjectMapper mapper = new ObjectMapper();
        Test test = mapper.readValue(jsonPrettyPrintString, Test.class);
        Timetable timetable = test.getTimetable();

        StringBuilder stringBuilder = new StringBuilder();

        //TODO Periods.class
        for (Period period1 : timetable.getPeriods().getPeriod()) {
            stringBuilder.append(period1);
            stringBuilder.append("\n");
            period1.setPeriods(timetable.getPeriods());
        }
        stringBuilder.append("\n");

        //TODO Daysdefs.class
        for (Sdef period1 : timetable.getDaysdefs().getDaysdef()) {
            stringBuilder.append(period1);
            stringBuilder.append("\n");
            period1.setDaysdefs(timetable.getDaysdefs());
        }

        //TODO Weeksdefs.class
        for (Building building : timetable.getWeeksdefs().getWeeksdef()) {
            stringBuilder.append("\n");
            stringBuilder.append(building);
            building.setWeeksdefs(timetable.getWeeksdefs());
        }
        stringBuilder.append("\n");

        //TODO Subjects.class
        for (Building building : timetable.getSubjects().getSubject()) {
            stringBuilder.append(building);
            stringBuilder.append("\n");
            building.setSubjects(timetable.getSubjects());
        }
        stringBuilder.append("\n");

        for (Teacher teacher1 : timetable.getTeachers().getTeacher()) {
            stringBuilder.append(teacher1);
            stringBuilder.append("\n");
            teacher1.setTeachers(timetable.getTeachers());
        }
        stringBuilder.append("\n");

        for (Building building1 : timetable.getBuildings().getBuilding()) {
            stringBuilder.append(building1);
            stringBuilder.append("\n");
            building1.setBuildings(timetable.getBuildings());
        }
        stringBuilder.append("\n");


        for (Classroom classroom1 : timetable.getClassrooms().getClassroom()) {
            stringBuilder.append(classroom1);
            stringBuilder.append("\n");
            classroom1.setClassrooms(timetable.getClassrooms());
        }
        stringBuilder.append("\n");


        for (Grade grade1 : timetable.getGrades().getGrade()) {
            stringBuilder.append(grade1);
            stringBuilder.append("\n");
            grade1.setGrades(timetable.getGrades());
        }
        stringBuilder.append("\n");


        for (ClassElement classesClass : timetable.getClasses().getClassesClass()) {
            stringBuilder.append(classesClass);
            stringBuilder.append("\n");
            classesClass.setClasses(timetable.getClasses());
        }
        stringBuilder.append("\n");

        for (Group group1 : timetable.getGroups().getGroup()) {
            stringBuilder.append(group1);
            stringBuilder.append("\n");
            group1.setGroups(timetable.getGroups());
        }
        stringBuilder.append("\n");

        stringBuilder.append(timetable.getStudents());
        stringBuilder.append("\n");
        stringBuilder.append(timetable.getStudentsubjects());
        stringBuilder.append("\n");

        for (Lesson stringMap : timetable.getLessons().getLesson()) {
            stringBuilder.append(stringMap);
            stringBuilder.append("\n");
            stringMap.setLessons(timetable.getLessons());
        }
        stringBuilder.append("\n");


        for (Card card1 : timetable.getCards().getCard()) {
            stringBuilder.append(card1);
            stringBuilder.append("\n");
            card1.setCards(timetable.getCards());
        }
        stringBuilder.append("\n");
        outWriter.write(stringBuilder.toString());
        outWriter.flush();
        outWriter.close();

        timetableRepo.save(timetable);


    }
}
