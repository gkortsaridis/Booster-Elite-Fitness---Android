package com.georgekortsaridis.boosterelitefitness;

/**
 * Created by george.kortsaridis on 01/04/2017.
 */

public class WOD {
    private String date;
    private String wod;

    public WOD(String date, String wod){
        this.date = date;
        this.wod = wod;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWod() {
        return wod;
    }

    public void setWod(String wod) {
        this.wod = wod;
    }
}
