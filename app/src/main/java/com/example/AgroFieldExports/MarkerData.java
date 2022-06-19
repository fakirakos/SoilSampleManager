package com.example.AgroFieldExports;

import com.google.android.gms.maps.model.LatLng;

public class MarkerData {
    long id;
    LatLng markerPoint;
    String uniqueMarkerId;
    private double markerPH;
    private double markerEC;
    private double markerCEC;
    private double markerPbs;
    private double markerOrganicMatter;
    private double markerNitrogen;
    private double markerIron;
    private double markerZinc;
    private double markerManganese;
    private double markerCopper;
    private double markerPotassium;
    private double markerPhosphorus;
    private double markerCalcium;
    private double markerMagnesium;
    private double markerSodium;
    private double markerSulfur;

    public MarkerData(long id, LatLng markerPoint, String uniqueMarkerId, double markerPH,
                      double markerEC, double markerCEC, double markerPbs, double markerOrganicMatter,
                      double markerNitrogen, double markerIron, double markerZinc, double markerManganese,
                      double markerCopper, double markerPotassium, double markerPhosphorus,
                      double markerCalcium, double markerMagnesium, double markerSodium, double markerSulfur) {
        this.id = id;
        this.markerPoint = markerPoint;
        this.uniqueMarkerId = uniqueMarkerId;
        this.markerPH = markerPH;
        this.markerEC = markerEC;
        this.markerCEC = markerCEC;
        this.markerPbs = markerPbs;
        this.markerOrganicMatter = markerOrganicMatter;
        this.markerNitrogen = markerNitrogen;
        this.markerIron = markerIron;
        this.markerZinc = markerZinc;
        this.markerManganese = markerManganese;
        this.markerCopper = markerCopper;
        this.markerPotassium = markerPotassium;
        this.markerPhosphorus = markerPhosphorus;
        this.markerCalcium = markerCalcium;
        this.markerMagnesium = markerMagnesium;
        this.markerSodium = markerSodium;
        this.markerSulfur = markerSulfur;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LatLng getMarkerPoint() {
        return markerPoint;
    }

    public void setMarkerPoint(LatLng markerPoint) {
        this.markerPoint = markerPoint;
    }

    public String getUniqueMarkerId() {
        return uniqueMarkerId;
    }

    public void setUniqueMarkerId(String uniqueMarkerId) {
        this.uniqueMarkerId = uniqueMarkerId;
    }

    public double getMarkerPH() {
        return markerPH;
    }

    public void setMarkerPH(double markerPH) {
        this.markerPH = markerPH;
    }

    public double getMarkerEC() {
        return markerEC;
    }

    public void setMarkerEC(double markerEC) {
        this.markerEC = markerEC;
    }

    public double getMarkerCEC() {
        return markerCEC;
    }

    public void setMarkerCEC(double markerCEC) {
        this.markerCEC = markerCEC;
    }

    public double getMarkerPbs() {
        return markerPbs;
    }

    public void setMarkerPbs(double markerPbs) {
        this.markerPbs = markerPbs;
    }

    public double getMarkerOrganicMatter() {
        return markerOrganicMatter;
    }

    public void setMarkerOrganicMatter(double markerOrganicMatter) {
        this.markerOrganicMatter = markerOrganicMatter;
    }

    public double getMarkerNitrogen() {
        return markerNitrogen;
    }

    public void setMarkerNitrogen(double markerNitrogen) {
        this.markerNitrogen = markerNitrogen;
    }

    public double getMarkerIron() {
        return markerIron;
    }

    public void setMarkerIron(double markerIron) {
        this.markerIron = markerIron;
    }

    public double getMarkerZinc() {
        return markerZinc;
    }

    public void setMarkerZinc(double markerZinc) {
        this.markerZinc = markerZinc;
    }

    public double getMarkerManganese() {
        return markerManganese;
    }

    public void setMarkerManganese(double markerManganese) {
        this.markerManganese = markerManganese;
    }

    public double getMarkerCopper() {
        return markerCopper;
    }

    public void setMarkerCopper(double markerCopper) {
        this.markerCopper = markerCopper;
    }

    public double getMarkerPotassium() {
        return markerPotassium;
    }

    public void setMarkerPotassium(double markerPotassium) {
        this.markerPotassium = markerPotassium;
    }

    public double getMarkerPhosphorus() {
        return markerPhosphorus;
    }

    public void setMarkerPhosphorus(double markerPhosphorus) {
        this.markerPhosphorus = markerPhosphorus;
    }

    public double getMarkerCalcium() {
        return markerCalcium;
    }

    public void setMarkerCalcium(double markerCalcium) {
        this.markerCalcium = markerCalcium;
    }

    public double getMarkerMagnesium() {
        return markerMagnesium;
    }

    public void setMarkerMagnesium(double markerMagnesium) {
        this.markerMagnesium = markerMagnesium;
    }

    public double getMarkerSodium() {
        return markerSodium;
    }

    public void setMarkerSodium(double markerSodium) {
        this.markerSodium = markerSodium;
    }

    public double getMarkerSulfur() {
        return markerSulfur;
    }

    public void setMarkerSulfur(double markerSulfur) {
        this.markerSulfur = markerSulfur;
    }
}
