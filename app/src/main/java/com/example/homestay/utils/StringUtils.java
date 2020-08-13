package com.example.homestay.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {
    public static String toRate(String rate){
        return rate + "₫";
    }

    public static String toRate(String rate, int promotion){
        int rateInt = Integer.parseInt(rate) * promotion / 100;
        return rateInt + "₫";
    }

    public static String cutString(String str, int length){
        if(str.length() > length){
            for(int i = length; i > length - 10; i --){
                if(str.charAt(i) == ' '){
                    return str.substring(0, i) + "..";
                }
            }
            return str.substring(0, length) + "..";
        }
        return str;
    }

    public static List<String> strToList(String str){
        String[] strings = str.split(";");
        return Arrays.asList(strings);
    }
}
