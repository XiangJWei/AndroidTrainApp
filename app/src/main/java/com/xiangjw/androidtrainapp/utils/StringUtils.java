package com.xiangjw.androidtrainapp.utils;

public class StringUtils {
    public static final String EMPTY = "";

    public static boolean isEmpty(String info){
        return info == null || EMPTY.equals(info);
    }

    public static boolean isNotEmpty(String info){
        return info != null && !EMPTY.equals(info);
    }

    public static String convert(String info){
        if(info == null){
            return EMPTY;
        }

        return info;
    }

    public static String convert(String info , String defaultStr){
        if(info == null){
            return defaultStr;
        }

        return info;
    }
}
