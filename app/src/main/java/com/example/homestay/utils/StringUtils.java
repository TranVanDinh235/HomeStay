package com.example.homestay.utils;

public class StringUtils {
    public static String toRate(String rate){
        return rate + "₫";
    }

    public static String toRate(String rate, int promotion){
        int rateInt = Integer.parseInt(rate) * promotion / 100;
        return rateInt + "₫";
    }

    public static String cutString(String str){
        if(str.length() > 50){
            for(int i = 50; i > 40; i --){
                if(str.charAt(i) == ' '){
                    return str.substring(0, i) + "..";
                }
            }
            return str.substring(0, 50) + "..";
        }
        return str;
    }
}
