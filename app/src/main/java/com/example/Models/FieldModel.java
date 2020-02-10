package com.example.Models;

import com.esri.arcgisruntime.geometry.PointCollection;

import java.util.Collections;
import java.util.List;

public class FieldModel {

    private PointCollection polygonPoints;
    String uniqueFieldID;
    String userChosenName;
    private List<CropModel> cropList;
    private List<MarkerModel> markerList;

    public FieldModel(PointCollection polygonPoints, String uniqueFieldID, String userChosenName, List<CropModel> cropList, List<MarkerModel> markerList) {
        this.polygonPoints = polygonPoints;
        this.uniqueFieldID = uniqueFieldID;
        this.userChosenName = userChosenName;
        this.cropList = cropList;
        this.markerList = markerList;
    }

    public FieldModel(PointCollection polygonPoints, String uniqueFieldID, String userChosenName){
        this.polygonPoints= polygonPoints;
        this.uniqueFieldID = uniqueFieldID;
        this.userChosenName = userChosenName;
        this.cropList= Collections.emptyList();
        this.markerList=Collections.emptyList();
    }
}
