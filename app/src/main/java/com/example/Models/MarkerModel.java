package com.example.Models;

import com.esri.arcgisruntime.geometry.Point;

public class MarkerModel {

    Point markerLocation;
    String uniqueMarkerId;
    String markerPH;
    String markerEC;
    String markerNitrogen;
    String markerPotassium;
    String markerPhosphorus;
    String markerCalcium;
    String markerMagnesium;

    public MarkerModel(Point markerLocation,String uniqueMarkerId, String markerPH, String markerEC,
                       String markerNitrogen, String markerPotassium, String markerPhosphorus, String markerCalcium, String markerMagnesium){
        this.markerLocation=markerLocation;
        this.uniqueMarkerId=uniqueMarkerId;
        this.markerPH=markerPH;
        this.markerEC=markerEC;
        this.markerNitrogen=markerNitrogen;
        this.markerPotassium=markerPotassium;
        this.markerPhosphorus=markerPhosphorus;
        this.markerCalcium=markerCalcium;
        this.markerMagnesium=markerMagnesium;
    }

    public Point getMarkerLocation() {
        return markerLocation;
    }

    public String getUniqueMarkerId() {
        return uniqueMarkerId;
    }
}
