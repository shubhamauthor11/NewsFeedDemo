package com.example.shubhamverma.newsfeeddemo.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "NEWS_FEED")
public class NewsFeedModel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Integer TEMP_ID;
    private String TITLE;
    private String AUTHOR;
    private String RESPONSE_JSON;
    private String SOURCE;
    private String DESCRIPTION;
    private String CONTENT_URL;
    private String IMAGE_URL;
    private String PUBLISHED_TIME;
    private String DATE_TIME;


    public NewsFeedModel() {
    }


    @NonNull
    public Integer getTEMP_ID() {
        return TEMP_ID;
    }

    public void setTEMP_ID(@NonNull Integer TEMP_ID) {
        this.TEMP_ID = TEMP_ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getRESPONSE_JSON() {
        return RESPONSE_JSON;
    }

    public void setRESPONSE_JSON(String RESPONSE_JSON) {
        this.RESPONSE_JSON = RESPONSE_JSON;
    }


    public String getIMAGE_URL() {
        return IMAGE_URL;
    }

    public void setIMAGE_URL(String IMAGE_URL) {
        this.IMAGE_URL = IMAGE_URL;
    }


    public String getSOURCE() {
        return SOURCE;
    }

    public void setSOURCE(String SOURCE) {
        this.SOURCE = SOURCE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getCONTENT_URL() {
        return CONTENT_URL;
    }

    public void setCONTENT_URL(String CONTENT_URL) {
        this.CONTENT_URL = CONTENT_URL;
    }

    public String getPUBLISHED_TIME() {
        return PUBLISHED_TIME;
    }

    public void setPUBLISHED_TIME(String PUBLISHED_TIME) {
        this.PUBLISHED_TIME = PUBLISHED_TIME;
    }

    public String getDATE_TIME() {
        return DATE_TIME;
    }

    public void setDATE_TIME(String DATE_TIME) {
        this.DATE_TIME = DATE_TIME;
    }

}
