package com.example.AgroFieldExports;

import android.graphics.Color;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class LandData {

    private long id;
    private long snapshot;
    private String title;
    private String tags;
    private String color;
    private List<LatLng> border;


    public LandData(long id, long snapshot, String title, String tags, String color, List<LatLng> border) {
        this.id = id;
        this.snapshot = snapshot;
        this.title = title;
        this.tags = tags;
        this.color = color;
        this.border = border;
    }

    public LandData() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(long snapshot) {
        this.snapshot = snapshot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {

        this.color = color;
    }

    public List<LatLng> getBorder() {
        return border;
    }

    public void setBorder(List<LatLng> border) {
        this.border = border;
    }
}
