package com.example.Models;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.example.RestfulWebService.MapCoordinatesDTO;
import com.example.RestfulWebService.MapDTO;
import com.example.RestfulWebService.MarkerDTO;

import java.util.ArrayList;
import java.util.List;

public class ModelsToDto {

    public MapDTO fieldModelToMapDto(FieldModel fieldInput) {
        MapDTO outputMap = new MapDTO();
        outputMap.setMapName(fieldInput.getUserChosenName());
        outputMap.setUniqueMapId(fieldInput.getUniqueFieldID());
        return outputMap;
    }

    public List<MapCoordinatesDTO> fieldModelToCoordinatesDto(FieldModel fieldInput) {
        List<MapCoordinatesDTO> outputCoordinates = new ArrayList<>();
        double x = 0;
        double y = 0;
        for (Point point : fieldInput.getPolygonPoints()) {
            x = point.getX();
            y = point.getY();
            outputCoordinates.add(new MapCoordinatesDTO(y, x));
        }
        return outputCoordinates;
    }

    public List<MarkerDTO> fieldModelToMarkerDto(FieldModel fieldInput) {
        List<MarkerDTO> outputMarkers = new ArrayList<>();
        for (MarkerModel marker : fieldInput.getMarkerList()) {
            outputMarkers.add(new MarkerDTO(marker.getUniqueMarkerId(),marker.getMarkerLocation().getY(), marker.getMarkerLocation().getX(),
                     marker.getMarkerPH(), marker.getMarkerEC(), marker.getMarkerCEC(), marker.getMarkerPbs(),
                    marker.getMarkerOrganicMatter(), marker.getMarkerNitrogen(), marker.getMarkerIron(), marker.getMarkerZinc(),
                    marker.getMarkerManganese(), marker.getMarkerCopper(), marker.getMarkerPotassium(), marker.getMarkerPhosphorus(),
                    marker.getMarkerCalcium(), marker.getMarkerMagnesium(), marker.getMarkerSodium(), marker.getMarkerSulfur()));
        }
        return outputMarkers;
    }
}
