package com.example.mobileassignment;

import android.media.Image;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Watchable {
    private String title;
    private int year;
    private String type;
    private String description;
    private Image cover;

    public Watchable(String title, int year, String type, String description, Image cover) {
        this.title = title;
        this.year = year;
        if(type.equalsIgnoreCase("Movie") || type.equalsIgnoreCase("Series")) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Type must be either 'Movie' or 'Series'.");
        }
        this.description = description;
        this.cover = cover;
    }

    public Watchable(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Watchable(String title, int year, String type) {
        this.title = title;
        this.year = year;
        if(type.equalsIgnoreCase("Movie") || type.equalsIgnoreCase("Series")) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Type must be either 'Movie' or 'Series'.");
        }

    }

    //Trying out New Feature to Create New User
    public void writeTOJson(Watchable watchable) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(watchable);
        mapper.writeValue((new File("user.json")),json);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getCover() {
        return cover;
    }

    public void setCover(Image cover) {
        this.cover = cover;
    }
}
