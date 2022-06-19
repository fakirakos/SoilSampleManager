package com.example.RestfulWebService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarkerDTO {
    @SerializedName("uniqueMarkerId")
    @Expose
    private String uniqueMarkerId;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("PH")
    @Expose
    private double pH;
    @SerializedName("EC")
    @Expose
    private double eC;
    @SerializedName("CEC")
    @Expose
    private double cEC;
    @SerializedName("percentbasesaturation")
    @Expose
    private double pbs;
    @SerializedName("nitrogen")
    @Expose
    private double nitrogen;
    @SerializedName("organicMatter")
    @Expose
    private double organicMatter;
    @SerializedName("iron")
    @Expose
    private double iron;
    @SerializedName("zinc")
    @Expose
    private double zinc;
    @SerializedName("manganese")
    @Expose
    private double manganese;
    @SerializedName("copper")
    @Expose
    private double copper;
    @SerializedName("potassium")
    @Expose
    private double potassium;
    @SerializedName("phosphorus")
    @Expose
    private double phosphorus;
    @SerializedName("calcium")
    @Expose
    private double calcium;
    @SerializedName("magnesium")
    @Expose
    private double magnesium;
    @SerializedName("sodium")
    @Expose
    private double sodium;
    @SerializedName("sulfur")
    @Expose
    private double sulfur;

    public MarkerDTO() {
    }

    public MarkerDTO(String uniqueMarkerId, double latitude, double longitude, double pH, double eC, double cEC, double pbs,
                     double nitrogen, double organicMatter, double iron, double zinc, double manganese, double copper,
                     double potassium, double phosphorus, double calcium, double magnesium, double sodium, double sulfur) {
        this.uniqueMarkerId = uniqueMarkerId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pH = pH;
        this.eC = eC;
        this.cEC = cEC;
        this.pbs = pbs;
        this.nitrogen = nitrogen;
        this.organicMatter = organicMatter;
        this.iron = iron;
        this.zinc = zinc;
        this.manganese = manganese;
        this.copper = copper;
        this.potassium = potassium;
        this.phosphorus = phosphorus;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.sodium = sodium;
        this.sulfur = sulfur;
    }


    public String getUniqueMarkerId() {
        return uniqueMarkerId;
    }

    public void setUniqueMarkerId(String uniqueMarkerId) {
        this.uniqueMarkerId = uniqueMarkerId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getpH() {
        return pH;
    }

    public void setpH(double pH) {
        this.pH = pH;
    }

    public double geteC() {
        return eC;
    }

    public void seteC(double eC) {
        this.eC = eC;
    }

    public double getcEC() {
        return cEC;
    }

    public void setcEC(double cEC) {
        this.cEC = cEC;
    }

    public double getPbs() {
        return pbs;
    }

    public void setPbs(double pbs) {
        this.pbs = pbs;
    }

    public double getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(double nitrogen) {
        this.nitrogen = nitrogen;
    }

    public double getOrganicMatter() {
        return organicMatter;
    }

    public void setOrganicMatter(double organicMatter) {
        this.organicMatter = organicMatter;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getZinc() {
        return zinc;
    }

    public void setZinc(double zinc) {
        this.zinc = zinc;
    }

    public double getManganese() {
        return manganese;
    }

    public void setManganese(double manganese) {
        this.manganese = manganese;
    }

    public double getCopper() {
        return copper;
    }

    public void setCopper(double copper) {
        this.copper = copper;
    }

    public double getPotassium() {
        return potassium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getSulfur() {
        return sulfur;
    }

    public void setSulfur(double sulfur) {
        this.sulfur = sulfur;
    }
}
