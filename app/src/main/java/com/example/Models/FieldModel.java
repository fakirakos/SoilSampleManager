package com.example.Models;

import com.esri.arcgisruntime.geometry.PointCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieldModel {

    private PointCollection polygonPoints;
    String uniqueFieldID;
    String userChosenName;
    private List<MarkerModel> markerList;

    public FieldModel() {
    }

    public FieldModel(PointCollection polygonPoints, String uniqueFieldID, String userChosenName){
        this.polygonPoints= polygonPoints;
        this.uniqueFieldID = uniqueFieldID;
        this.userChosenName = userChosenName;
        this.markerList=new ArrayList<>();
    }

    public void addMarker(MarkerModel marker){
        markerList.add(marker);

    }

    public PointCollection getPolygonPoints(){
        return polygonPoints;
    }


    public List<MarkerModel> getMarkerList() {
        return markerList;
    }

    public void setMarkerList(List<MarkerModel> markerList) {
        this.markerList = markerList;
    }

    public void setPolygonPoints(PointCollection polygonPoints) {
        this.polygonPoints = polygonPoints;
    }

    public String getUniqueFieldID() {
        return uniqueFieldID;
    }

    public void setUniqueFieldID(String uniqueFieldID) {
        this.uniqueFieldID = uniqueFieldID;
    }

    public String getUserChosenName() {
        return userChosenName;
    }

    public void setUserChosenName(String userChosenName) {
        this.userChosenName = userChosenName;
    }

}
