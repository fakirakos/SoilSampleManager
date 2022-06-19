package com.example.RestfulWebService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MapDTO {
    @SerializedName("uniqueMapId")
    @Expose
    private String uniqueMapId;
    @SerializedName("mapName")
    @Expose
    private String mapName;


    public MapDTO() {
    }

    public MapDTO(String uniqueMapId, String mapName){
        this.uniqueMapId=uniqueMapId;
        this.mapName=mapName;
    }

    public MapDTO(String uniqueMapId) {
        this.uniqueMapId = uniqueMapId;
    }

    public String getUniqueMapId() {
        return uniqueMapId;
    }

    public void setUniqueMapId(String uniqueMapId) {
        this.uniqueMapId = uniqueMapId;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
}
