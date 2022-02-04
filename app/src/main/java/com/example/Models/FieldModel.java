package com.example.Models;

import com.esri.arcgisruntime.geometry.PointCollection;

import java.util.Collections;
import java.util.List;

public class FieldModel {

    private PointCollection polygonPoints;
    String uniqueFieldID;
    String userChosenName;
    private List<MarkerModel> markerList;

    public FieldModel(PointCollection polygonPoints, String uniqueFieldID, String userChosenName, List<MarkerModel> markerList) {
        this.polygonPoints = polygonPoints;
        this.uniqueFieldID = uniqueFieldID;
        this.userChosenName = userChosenName;
        this.markerList = markerList;
    }

    public FieldModel(PointCollection polygonPoints, String uniqueFieldID, String userChosenName){
        this.polygonPoints= polygonPoints;
        this.uniqueFieldID = uniqueFieldID;
        this.userChosenName = userChosenName;
        this.markerList=Collections.emptyList();
    }

    public void addMarker(MarkerModel marker){
        markerList.add(marker);

    }

    public List<MarkerModel> getMarkerList() {
        return markerList;
    }

    public void setMarkerList(List<MarkerModel> markerList) {
        this.markerList = markerList;
    }
}
