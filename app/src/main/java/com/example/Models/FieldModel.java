package com.example.Models;

import com.esri.arcgisruntime.geometry.Point;

import java.util.List;

public class FieldModel {

    private List<Point> polygonPoints;
    String uniqueFieldID;
    String userChosenName;
    private List<CropModel> cropList;
    private List<MarkerModel> markerList;

    public FieldModel(List<Point> polygonPoints, String uniqueFieldID, String userChosenName, List<CropModel> cropList, List<MarkerModel> markerList) {
        this.polygonPoints = polygonPoints;
        this.uniqueFieldID = uniqueFieldID;
        this.userChosenName = userChosenName;
        this.cropList = cropList;
        this.markerList = markerList;
    }
}
