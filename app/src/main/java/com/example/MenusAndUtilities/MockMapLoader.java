package com.example.MenusAndUtilities;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.example.Models.FieldModel;
import com.example.Models.MarkerModel;

import org.apache.logging.log4j.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockMapLoader {


        public List<MarkerModel> makeMockMarkers(){
            List<MarkerModel> markerList=new ArrayList<>();
            markerList.add(new MarkerModel(new Point(23.5586357, 41.1279869, SpatialReferences.getWgs84()),"mockMarker1",
                    6.9, 230, 15, 32, 2.5,4, 26.6, 1.8,
                    14.6, 1, 455, 49, 2328, 423,
                    68, 20));
            markerList.add(new MarkerModel(new Point(23.5624123, 41.1254008,SpatialReferences.getWgs84()), "mockMarker2",
                    7.4, 215, 25, 16, 3.1,4.5, 32.8, 2.4,
                    9.6, 2.5, 325, 52, 2156, 379,
                    85, 17));
            return markerList;
        }

        public FieldModel getMockModelWithMarkers(List<MarkerModel> mockMarkerList){
            PointCollection newPoints=new PointCollection(SpatialReferences.getWgs84());
            newPoints.add(23.5514259, 41.1322538);
            newPoints.add(23.5509109, 41.1322538);
            newPoints.add(23.5524559, 41.1215215);
            newPoints.add(23.5706520, 41.1234612);
            newPoints.add(23.5689354, 41.1326417);
            newPoints.add(23.5514259, 41.1322538);
            String uuid= String.valueOf(UUID.randomUUID());
            String name="Mock Map Name";
            FieldModel mockModel= new FieldModel(newPoints,uuid,name);
            for(MarkerModel temp: mockMarkerList){
                mockModel.addMarker(temp);
            }
            return mockModel;
        }


    public FieldModel getMockModel(){
        PointCollection newPoints=new PointCollection(SpatialReferences.getWgs84());
        newPoints.add(23.5514259, 41.1322538);
        newPoints.add(23.5509109, 41.1322538);
        newPoints.add(23.5524559, 41.1215215);
        newPoints.add(23.5706520, 41.1234612);
        newPoints.add(23.5689354, 41.1326417);
        newPoints.add(23.5514259, 41.1322538);
        String uuid= String.valueOf(UUID.randomUUID());
        String name="Mock Map Name";
        FieldModel mockModel= new FieldModel(newPoints,uuid,name);
        return mockModel;
    }

    }

