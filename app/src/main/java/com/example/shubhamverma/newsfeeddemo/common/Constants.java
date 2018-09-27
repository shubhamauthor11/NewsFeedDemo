package com.example.shubhamverma.newsfeeddemo.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants {

    public static int requestTimeOutInSeconds = 600;

    public static final String currentDate() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       final String currentDateandTime = sdf.format(new Date());

        return currentDateandTime;
    }

}
