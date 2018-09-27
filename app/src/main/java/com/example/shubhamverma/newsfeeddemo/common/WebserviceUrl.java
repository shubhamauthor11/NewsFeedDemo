package com.example.shubhamverma.newsfeeddemo.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WebserviceUrl {


    public static final String getNewsData = "https://newsapi.org/";
    public static final String date = Constants.currentDate();
//    public static final String apiKey = "v2/everything?q=bitcoin&from=2018-08-27&sortBy=publishedAt&apiKey=ed1eb10d6f354f86849738792591cfa4";

//    public static final String apiKey = "v2/everything?q=bitcoin&from="+date+"&sortBy=publishedAt&apiKey=ed1eb10d6f354f86849738792591cfa4";

 public static final String apiKey = "https://newsapi.org/v2/everything?q=bitcoin&from="+date+"&sortBy=publishedAt&apiKey=ed1eb10d6f354f86849738792591cfa4";
}
