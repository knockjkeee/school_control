package com.verification.parseschedule.service;

import com.verification.parseschedule.CompareUtils;
import com.verification.parseschedule.domain.*;
import com.verification.parseschedule.repo.TimetableRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class VerificationDataTime {

    private TimetableRepo timetableRepo;

    public VerificationDataTime(TimetableRepo timetableRepo) {
        this.timetableRepo = timetableRepo;
    }

    private LocalDateTime dateTime = LocalDateTime.now();




    public void checkData() {
        String transit = "2019-12-03 12:00:53.0";

        Calendar calendar = new GregorianCalendar();
        String weekName = calendar.getDisplayName
                (Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("ru"));

        String entime = CompareUtils.getPeriodEndTime(transit);
//        System.out.println(CompareUtils.getPeriodEndTime(transit));

//        String entime = "14:50"; //Todo field from DATABASE
        String name = "9-И".replaceAll("-","").toUpperCase().trim(); //Todo field from DATABASE

        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        String weekSd = getNumberWeeks(weekOfMonth);


        List<Timetable> list = timetableRepo.findAll();
        Timetable timetable = list.get(list.size() - 1);
//        System.out.println(timetable);

        List<Building> subject = timetable.getSubjects().getSubject();

        ClassElement classesName = timetable.getClasses().getClassesClass()
                .stream().filter(classElement -> classElement.getName().equals(name))
                .findFirst().orElse(new ClassElement());

        System.out.println(classesName);

        List<Lesson> collect = timetable.getLessons().getLesson().stream().filter(
                lesson -> lesson.getClassids().equals(classesName.getId()))
                .collect(Collectors.toList());

        List<Teacher> teacher = timetable.getTeachers().getTeacher();

        Period periodTime = timetable.getPeriods().getPeriod().stream().filter(
                period -> period.getEndtime().equals(entime)).findFirst().orElse(new Period());

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

        //System.out.println(classesName);
        //System.out.println(collect);
        //System.out.println("**********************");
//        System.out.println(listCards);
//        System.out.println(listCards.size());

        //System.out.println(listCards.stream().filter(card -> card.getWeeks().equals("10")).collect(Collectors.toList()));
        //System.out.println(listCards.stream().filter(card -> card.getWeeks().equals("10")).collect(Collectors.toList()).size());

        //System.out.println(listCards.stream().filter(card -> card.getWeeks().equals("01")).collect(Collectors.toList()));
        //System.out.println(listCards.stream().filter(card -> card.getWeeks().equals("01")).collect(Collectors.toList()).size());

        //System.out.println("**********************");

        List<String> listPeriod = getListPeriod(periodTime.getPeriod());
        List<Card> colFirst = new ArrayList<>();
//        List<Card> colSecond = new ArrayList<>();
//        List<Teacher> teachersFirst = new ArrayList<>();
//        List<Teacher> teachersSecond = new ArrayList<>();


        if (listPeriod == null) {
            //todo exit good side

        } else {
            for (String period : listPeriod) {
                colFirst.addAll(listCards.stream().filter(
                        card -> card.getWeeks().equals(weeks.getWeeks())
                                && card.getDays().equals(day.getDays())
                                && card.getPeriod().equals(period))
                        .collect(Collectors.toList()));
//                colSecond.addAll(listCards.stream().filter(
//                        card -> card.getWeeks().equals(weeks.getWeeks())
//                                && card.getDays().equals(day.getDays())
//                                && card.getPeriod().equals(period))
//                        .collect(Collectors.toList()));
            }
            //todo check lesson name and teacher name
            System.out.println(colFirst);

            List<String> strings = toStringData(subject, collect, colFirst, teacher, timetable);
            System.out.println(strings.size());
            String s = strings.toString();
            System.out.println(s);

        }

        /*List<Card> colFirst = listCards.stream().filter(
                card -> card.getWeeks().equals("10")
                        && card.getDays().equals(day.getDays())
                        && card.getPeriod().equals(periodTime.getPeriod()))
                .collect(Collectors.toList());

        List<Card> colSecond = listCards.stream().filter(
                card -> card.getWeeks().equals("01")
                        && card.getDays().equals(day.getDays())
                        && card.getPeriod().equals(periodTime.getPeriod()))
                .collect(Collectors.toList());*/




//        toStringData(subject, collect, colSecond, teacher);


//        //System.out.println(periodTime.getPeriod());
//        //System.out.println(day.getDays());
//        //System.out.println(subject);

//        //System.out.println(listPeriod);

//  TODO      if (6) {
//            1
//        }


//        //System.out.println(teacher);
        //System.out.println(weekName);
        //System.out.println(weekOfMonth);
        //System.out.println(weeks);
    }

    private String getNumberWeeks(int weekOfMonth) {
        return (weekOfMonth % 2 == 0) ? "Неделя 2" : "Неделя 1";
    }

    private List<String> toStringData(List<Building> subject, List<Lesson> collect, List<Card> col, List<Teacher> teachers, Timetable timetable ) {
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
//        String s = currentPeriod.toString();
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
