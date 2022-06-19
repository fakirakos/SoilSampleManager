package com.example.AgroFieldExports;

import java.util.ArrayList;
import java.util.List;

public class Land {

    private LandData data;
    private boolean selected;
    private String tag;


    public Land(LandData data){
        this.data = data;
        this.selected=false;
        this.tag = "";
        List<MarkerData> markerDataList=new ArrayList<>();
    }


    public LandData getData() {
        return data;
    }

    public void setData(LandData data) {
        this.data = data;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
