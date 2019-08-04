package com.example.myapplication;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.util.Locale;

public class Date {
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getDate() {
        java.util.Date d=new java.util.Date();
        String date=new SimpleDateFormat("yyyy-MM-dd").format(d);
        System.out.println(date);
        return date;
    }

}


