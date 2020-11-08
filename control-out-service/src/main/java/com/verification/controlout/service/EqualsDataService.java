package com.verification.controlout.service;


import com.verification.controlout.domain.result.*;
import com.verification.controlout.domain.transit.Client;
import com.verification.controlout.domain.transit.ClientsGroup;
import com.verification.controlout.domain.transit.ClientsPhoto;
import com.verification.controlout.domain.transit.Transit;
import com.verification.controlout.repo.transit.ClientRepo;
import com.verification.controlout.repo.transit.ClientsGroupRepo;
import com.verification.controlout.repo.transit.ClientsPhotoRepo;
import com.verification.controlout.repo.timerable.TimetableRepo;
import com.verification.controlout.util.CompareUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope(value = "prototype")
public class EqualsDataService {
    private static final Logger logger = LoggerFactory.getLogger(EqualsDataService.class);


    private TimetableRepo timetableRepo;
    private ClientRepo clientRepo;
    private ClientsPhotoRepo clientsPhotoRepo;
    private SaveData saveData;
    private ClientsGroupRepo clientsGroupRepo;

    public EqualsDataService(TimetableRepo timetableRepo, ClientRepo clientRepo, ClientsPhotoRepo clientsPhotoRepo, SaveData saveData, ClientsGroupRepo clientsGroupRepo) {
        this.timetableRepo = timetableRepo;
        this.clientRepo = clientRepo;
        this.clientsPhotoRepo = clientsPhotoRepo;
        this.saveData = saveData;
        this.clientsGroupRepo = clientsGroupRepo;
    }


    public void createData(Transit transit) {
        Calendar calendar = new GregorianCalendar();
        String weekName = calendar.getDisplayName
                (Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("ru"));
        Date currentTime = calendar.getTime();

        boolean isTime = false;

        try {
            isTime = CompareUtils.equalsTimeData(transit, currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        String entime = "14:50"; //Todo field from DATABASE
        String endtime = CompareUtils.getPeriodEndTime(transit);

        logger.error("String endtime " + endtime);

        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        String weekSd = getNumberWeeks(weekOfMonth);

        List<Timetable> list = timetableRepo.findAll();
        logger.error("List<Timetable> " + String.valueOf(list.size()));

        Timetable timetable = list.get(list.size() - 1);
        logger.error("Timetable" + timetable.getIdentifier());

        Client client = clientRepo.findByIdOfClient(transit.getIdOfClient());
        logger.error("Client " + String.valueOf(client));

        ClientsPhoto clientsPhoto = clientsPhotoRepo.findByIdOfClient(transit.getIdOfClient());
        logger.error("ClientsPhoto " + String.valueOf(clientsPhoto));

        ClientsGroup clientsGroup = clientsGroupRepo.findByIdOfClientsGroup(client.getClientsGroupId());
        logger.error("ClientsGroup " + String.valueOf(clientsGroup));

        String nameClass = clientsGroup.getName();
        logger.error("String nameClass before " + nameClass);

        String blackNameClass = "МШФ6_2019, Пед. состав, Администрация, Тех. персонал, Родители, " +
                "Посетители, Другое, Выбывшие, Удаленные, Перемещенные, Обучающиеся других ОО, " +
                "Родители обучающихся других ОО, Сотрудники других ОО, спортсмены, столовая";

        if (clientsGroup != null) {
            if (!blackNameClass.contains(nameClass)) {
                if (!endtime.equals("null")) {
                    //        String name = "10В"; //Todo field from DATABASE  SPLIT(- "")
                    String names = nameClass.replaceAll("-", "").toUpperCase().trim();
                    logger.error("String nameClass after replace " + names);

                    List<Building> subject = timetable.getSubjects().getSubject();

                    ClassElement classesName = timetable.getClasses().getClassesClass()
                            .stream().filter(classElement -> classElement.getName().equals(names))
                            .findFirst().orElse(new ClassElement());
                    logger.error("ClassElement classesName " + classesName);

                    List<Lesson> collect = timetable.getLessons().getLesson().stream().filter(
                            lesson -> lesson.getClassids().equals(classesName.getId()))
                            .collect(Collectors.toList());
//                    logger.error("List<Lesson> collect " + collect);

                    List<Teacher> teacher = timetable.getTeachers().getTeacher();

                    Period periodTime = timetable.getPeriods().getPeriod().stream().filter(
                            period -> period.getEndtime().equals(endtime)).findFirst().orElse(new Period());


                    Sdef day = timetable.getDaysdefs().getDaysdef().stream()
                            .filter(sdef -> sdef.getName().toLowerCase().equals(weekName)).findFirst().orElse(new Sdef());

                    Building weeks = timetable.getWeeksdefs().getWeeksdef().stream()
                            .filter(building -> building.getName().equals(weekSd)).findFirst().orElse(new Building());

                    List<Card> listCards = new ArrayList<>();

                    for (Lesson lesson : collect) {
                        List<Card> cards;
                        cards = timetable.getCards().getCard().stream().filter(
                                card -> card.getLessonid().equals(lesson.getId())).collect(Collectors.toList());
                        listCards.addAll(cards);
                    }
//                    logger.error("List<Card> listCards " + listCards);

                    List<String> listPeriod = getListPeriod(periodTime.getPeriod());
                    List<Card> colFirst = new ArrayList<>();

                    logger.error("List<String> listPeriod " + listPeriod);
                    logger.error("List<String> listPeriod.size() " + listPeriod.size());

                    if (listPeriod == null) {
                        //todo exit good side
                        saveData.save(transit, client, clientsPhoto, clientsGroup, "Ок", new ArrayList<>());

                    } else {
                        for (String period : listPeriod) {
                            colFirst.addAll(listCards.stream().filter(
                                    card -> card.getWeeks().equals(weeks.getWeeks())
                                            && card.getDays().equals(day.getDays())
                                            && card.getPeriod().equals(period))
                                    .collect(Collectors.toList()));
                        }

                        List<String> lessons = toStringData(subject, collect, colFirst, teacher, timetable);

                        //todo check lesson name and teacher name
//                        logger.error("List<Card> colFirst" + colFirst);
//
//                        logger.error(" List<String> lessons " + lessons);

                        if (lessons == null || lessons.size() == 0) {
                            saveData.save(transit, client, clientsPhoto, clientsGroup, "Ок", lessons);
                        } else {
                            saveData.save(transit, client, clientsPhoto, clientsGroup, "Прогул", lessons);
                        }
                    }
                } else {
                    saveData.save(transit, client, clientsPhoto, clientsGroup, "Ок", new ArrayList<>());
                }


            }
        }
    }

    private String getNumberWeeks(int weekOfMonth) {
        return (weekOfMonth % 2 == 0) ? "Неделя 1" : "Неделя 2";
    }

    private List<String> toStringData(List<Building> subject, List<Lesson> collect, List<Card> col, List<Teacher> teachers, Timetable timetable) {
        List<String> res = new ArrayList<>();
//        System.out.println(col);
        col.forEach(card -> {
            StringBuilder temp = new StringBuilder();
//            System.out.println(card.getPeriod());
//            System.out.println(card.getPeriod());
            Period per = timetable.getPeriods().getPeriod().stream().filter(period -> period.getPeriod().equals(card.getPeriod())).findFirst().orElse(new Period());

//            System.out.println(per.getStarttime());
            temp.append(per.getStarttime());

            collect.stream().filter(lesson -> lesson.getId().equals(card.getLessonid())).
                    collect(Collectors.toList()).forEach(lesson -> {
//                System.out.println(lesson);

                subject.stream().filter(building ->
                        lesson.getSubjectid().equals(building.getId())).forEach(building -> {

//                    System.out.println(building.getName());
                    temp.append(" " + building.getName());

                });
                teachers.stream().filter(teacher ->
                        teacher.getId().equals(lesson.getTeacherids())).forEach(teacher -> {
//                    System.out.println(teacher.getName());
                    temp.append(" " + teacher.getName());

                });
            });
            res.add(temp.toString());
        });
//        System.out.println(res);
        return res;
    }


    private List<String> getListPeriod(String currentPeriod) {
        List<String> temp = null;
        if (currentPeriod == null) {
            currentPeriod = "null";
        }
        switch (currentPeriod) {
            case ("0"):
                temp = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
                break;
            case ("1"):
                temp = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
                break;
            case ("2"):
                temp = Arrays.asList("2", "3", "4", "5", "6", "7", "8");
                break;
            case ("3"):
                temp = Arrays.asList("3", "4", "5", "6", "7", "8");
                break;
            case ("4"):
                temp = Arrays.asList("4", "5", "6", "7", "8");
                break;
            case ("5"):
                temp = Arrays.asList("5", "6", "7", "8");
                break;
            case ("6"):
                temp = Arrays.asList("6", "7", "8");
                break;
            case ("7"):
                temp = Arrays.asList("7", "8");
                break;
            case ("8"):
                temp = Collections.singletonList("8");
                break;
            default:
                temp = null;
        }
        return temp;
    }
}


