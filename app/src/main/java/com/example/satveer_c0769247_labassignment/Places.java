package com.example.satveer_c0769247_labassignment;

public class Places {
    int id;
    String name,longitude,latitude,date;

    public Places(int id, String name, String longitude, String latitude, String date) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getlongitude() {
        return longitude;
    }

    public String getlatitude() {
        return latitude;
    }

    public String getDate() {
        return date;
    }

}
