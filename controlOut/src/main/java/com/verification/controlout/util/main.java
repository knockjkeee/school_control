package com.verification.controlout.util;

public class main {

    public static void main(String[] args) {
        System.out.println(getNumberWeeks(1));
    }



    private static String getNumberWeeks(int weekOfMonth) {
        return (weekOfMonth % 2 == 0) ? "Неделя 2" : "Неделя 1";
    }
}
