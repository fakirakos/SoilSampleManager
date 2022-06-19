package com.example.AgroFieldExports;

import android.graphics.Color;
import android.os.Environment;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.example.Models.FieldModel;
import com.example.Models.MarkerModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LandDataConverter {

    public static LandData FieldModelToLandData(FieldModel modelInput){
        long id=0;
        long snapshot=-1;
        double x=0;
        double y=0;
        String tags="";
        String title=modelInput.getUserChosenName();
        List<LatLng> convertedPoints= new ArrayList<>();
        for(Point point:modelInput.getPolygonPoints()){
            x= point.getX();
            y= point.getY();
        convertedPoints.add(new LatLng(y,x));
        }
        String color="#0004ff";
        LandData convertedLandData=new LandData(id,snapshot,title,tags,color,convertedPoints);
        return convertedLandData;
    }
    
    public static List<MarkerData> FieldModelMarkersToMarkerData(FieldModel modelInput){
        List<MarkerData> markerDataList=new ArrayList<>();
        List<MarkerModel> markerModelList=new ArrayList<>();
        markerModelList=modelInput.getMarkerList();
        for (int i=0; i<markerModelList.size(); i++) {
            MarkerModel tempMarker= markerModelList.get(i);
            Point temp=tempMarker.getMarkerLocation();
            LatLng markerDataPoint= new LatLng(temp.getY(),temp.getX());
            MarkerData createdMarker= new MarkerData((long) i, markerDataPoint, tempMarker.getUniqueMarkerId(),
                    tempMarker.getMarkerPH(), tempMarker.getMarkerEC(), tempMarker.getMarkerCEC(),
                    tempMarker.getMarkerPbs(),tempMarker.getMarkerOrganicMatter(), tempMarker.getMarkerNitrogen(),
                    tempMarker.getMarkerIron(),tempMarker.getMarkerZinc(), tempMarker.getMarkerManganese(),
                    tempMarker.getMarkerCopper(), tempMarker.getMarkerPotassium(), tempMarker.getMarkerPhosphorus(),
                    tempMarker.getMarkerCalcium(), tempMarker.getMarkerMagnesium(), tempMarker.getMarkerSodium(),
                    tempMarker.getMarkerSulfur());
            markerDataList.add(createdMarker);
        }
        return markerDataList;
    }

    public static String pointsPrettyPrint(List<List<LatLng>> pointsList){
        List<List<double[]>> ans = new ArrayList<>();
        List<double[]> row;
        for(List<LatLng> points:pointsList){
            row = new ArrayList<>();
            for(LatLng point : points){
                row.add(new double[]{point.longitude,point.latitude});
            }
            if(points.size()>1){
                if(!points.get(0).equals(points.get(points.size()-1))){
                    row.add(new double[]{points.get(0).longitude,points.get(0).latitude});
                }
            }
            ans.add(row);
        }
        return new Gson().toJson(ans);
    }
    

    public static boolean dbExportAFileFileXLS(List<Land> lands, List<MarkerData> markers) throws IOException {
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
        );
        String fileName = "export_"+(System.currentTimeMillis()/1000)+".xls";
        File mFile = new File(path, fileName);

        FileOutputStream outputStream = new FileOutputStream(mFile);
        if(lands.size()>0){
            return OpenXmlDataBaseOutput.exportDBXLS(outputStream, lands, markers);
        }
        return false;
    }
}
