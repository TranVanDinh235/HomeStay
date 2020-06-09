package com.example.homestay.utils;

import com.example.homestay.R;

public class IconUtil {
    public static int getIconResource(String name){
        switch (name){
            case "wifi": return R.drawable.ic_wifi;
            case "TV": return R.drawable.ic_tv;
            case "air_conditioner": return R.drawable.ic_air_conditioner;
            case "washing_machine": return R.drawable.ic_washing_machine;
            case "shampoo": return R.drawable.ic_shampoo;
            case "toilet_paper": return R.drawable.ic_toilet_paper;
            case "tissue": return R.drawable.ic_tissue_paper;
            case "mineral_water": return R.drawable.ic_mineral_water;
            case "toothpaste": return R.drawable.ic_toothpaste;
            case "soap": return R.drawable.ic_soap;
            case "towel": return R.drawable.ic_tower;
            case "dryer": return R.drawable.ic_dryer;
            case "shower_gel": return R.drawable.ic_shower_gel;
        }
        return R.drawable.ic_account;
    }
}
