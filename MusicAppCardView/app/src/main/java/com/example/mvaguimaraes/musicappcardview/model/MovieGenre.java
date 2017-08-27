package com.example.mvaguimaraes.musicappcardview.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mvaguimaraes on 8/25/17.
 */

public class MovieGenre {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
