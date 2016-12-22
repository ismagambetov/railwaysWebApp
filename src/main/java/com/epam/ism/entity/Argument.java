package com.epam.ism.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Argument {
    private static List<Object> list = new ArrayList<>();

    public static void add(Object o) {
        list.add(o);
    }

    public static List<Object> get() {
        return list;
    }
}
