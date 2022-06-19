package com.example.Models;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.example.RestfulWebService.MapCoordinatesDTO;
import com.example.RestfulWebService.MapDTO;
import com.example.RestfulWebService.MarkerDTO;

import java.util.ArrayList;
import java.util.List;

public class DtoToModels {


    public FieldModel mapDtoToFieldModel(MapDTO inputMapDto, List<MapCoordinatesDTO> inputCoordinatesDto){
        FieldModel outputField = new FieldModel();
        outputField.setUniqueFieldID(inputMapDto.getUniqueMapId());
        outputField.setUserChosenName(inputMapDto.getMapName());
        PointCollection fieldBorder= new PointCollection(SpatialReferences.getWgs84());
        for(MapCoordinatesDTO item:inputCoordinatesDto){
            fieldBorder.add(item.getLongitude(), item.getLatitude());
        }
        outputField.setPolygonPoints(fieldBorder);
        outputField.setMarkerList(new ArrayList<>());
        return outputField;
    }

    public FieldModel mapDtoToFieldModelWithMarkers(MapDTO inputMapDto, List<MapCoordinatesDTO> inputCoordinatesDto, List<MarkerDTO> inputMarkersDto){
        FieldModel outputField = new FieldModel();
        outputField.setUniqueFieldID(inputMapDto.getUniqueMapId());
        outputField.setUserChosenName(inputMapDto.getMapName());
        PointCollection fieldBorder= new PointCollection(SpatialReferences.getWgs84());
        for(MapCoordinatesDTO item:inputCoordinatesDto){
            fieldBorder.add(item.getLongitude(), item.getLatitude());
        }
        outputField.setPolygonPoints(fieldBorder);
        List<MarkerModel> fieldMarkerList= new ArrayList<>();
        for(MarkerDTO marker:inputMarkersDto ){
            fieldMarkerList.add(new MarkerModel(new Point(marker.getLongitude(),marker.getLatitude(), SpatialReferences.getWgs84()),
                    marker.getUniqueMarkerId(),marker.getpH(),marker.geteC(),marker.getcEC(),marker.getPbs(),marker.getOrganicMatter(),
                    marker.getNitrogen(),marker.getIron(),marker.getZinc(),marker.getManganese(),marker.getCopper(),
                    marker.getPotassium(),marker.getPhosphorus(),marker.getCalcium(),marker.getMagnesium(),
                    marker.getSodium(),marker.getSulfur()));
        }
        outputField.setMarkerList(fieldMarkerList);
        return outputField;
    }
}
