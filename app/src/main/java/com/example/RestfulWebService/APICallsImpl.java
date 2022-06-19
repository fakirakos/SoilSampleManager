package com.example.RestfulWebService;

import android.content.Context;
import android.widget.Toast;

import com.example.MapActivities.MapActivity;
import com.example.MenusAndUtilities.AdapterRecycler;
import com.example.MenusAndUtilities.MapChoiceDialog;
import com.example.Models.DtoToModels;
import com.example.Models.FieldModel;
import com.example.Models.ModelsToDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class APICallsImpl {
    Context mctx;
    boolean coordinatesReturned=false;
    boolean markersReturned=false;
    List<MapCoordinatesDTO> mapCoordinatesDTOs;
    List<MarkerDTO> mapMarkersDTOs;
    MapDTO mapDto;
    FieldModel outputField;
    private FieldModelLoadedListener listener;


    public APICallsImpl(Context context) {

        this.mctx=context;
        listener= (FieldModelLoadedListener) mctx;
    }
    public void getAllUserMaps(String uuid){
        ApiEndpointService service=RetrofitClient.getUserService();

        Call<List<MapDTO>> callAsync=service.getAllUserMaps(uuid);

        callAsync.enqueue(new Callback<List<MapDTO>>() {
            @Override
            public void onResponse(Call<List<MapDTO>> call, Response<List<MapDTO>> response) {
                if(response.isSuccessful()){
                    List<MapDTO> apiResponse=response.body();
                    MapChoiceDialog dialogChoice= new MapChoiceDialog(mctx,apiResponse);
                    dialogChoice.showDialog(mctx);

                    //TODO calls correctly, now to pass to choice dialog


                }
            }

            @Override
            public void onFailure(Call<List<MapDTO>> call, Throwable t) {
                System.out.println("Network Error :: " + t.getLocalizedMessage());
            }
        });
    }

    public void getChosenUserMapInfo(MapDTO chosenMap){
        ApiEndpointService service=RetrofitClient.getUserService();
        mapCoordinatesDTOs=new ArrayList<>();
        mapMarkersDTOs =new ArrayList<>();
        coordinatesReturned=false;
        markersReturned=false;
        DtoToModels dtoToModels=new DtoToModels();
        String chosenMapId=chosenMap.getUniqueMapId();
        outputField=new FieldModel();

        Call<List<MapCoordinatesDTO>> callCoordinatesAsync = service.getCoordinatesOfMap(chosenMapId);
        Call<List<MarkerDTO>> callMarkersAsync = service.getAllMarkerByMapUuid(chosenMapId);

        callCoordinatesAsync.enqueue(new Callback<List<MapCoordinatesDTO>>(){
            @Override
            public void onResponse(Call<List<MapCoordinatesDTO>> call, Response<List<MapCoordinatesDTO>> response){
                if(response.isSuccessful()){
                    coordinatesReturned=true;
                    mapCoordinatesDTOs=response.body();
                    if(coordinatesReturned && markersReturned){
                        if(mapMarkersDTOs.isEmpty()){
                            outputField=dtoToModels.mapDtoToFieldModel(chosenMap,mapCoordinatesDTOs);
                        }
                        else{
                            outputField=dtoToModels.mapDtoToFieldModelWithMarkers(chosenMap,mapCoordinatesDTOs, mapMarkersDTOs);
                        }
                        listener.loadField(outputField);

                    }

                }

            }
            @Override
            public void onFailure(Call<List<MapCoordinatesDTO>> call, Throwable t){
                System.out.println("Network Error :: " + t.getLocalizedMessage());
            }
        });

        callMarkersAsync.enqueue(new Callback<List<MarkerDTO>>() {
            @Override
            public void onResponse(Call<List<MarkerDTO>> call, Response<List<MarkerDTO>> response) {
                if(response.isSuccessful()){
                    markersReturned=true;
                    mapMarkersDTOs =response.body();
                    if(coordinatesReturned && markersReturned){
                        if(mapMarkersDTOs.isEmpty()){
                            outputField=dtoToModels.mapDtoToFieldModel(chosenMap,mapCoordinatesDTOs);
                        }
                        else{
                            outputField=dtoToModels.mapDtoToFieldModelWithMarkers(chosenMap,mapCoordinatesDTOs, mapMarkersDTOs);
                        }
                        listener.loadField(outputField);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MarkerDTO>> call, Throwable t) {

            }
        });

    }

    public void saveMapAndInfo(FieldModel fieldInput){
        ApiEndpointService service=RetrofitClient.getUserService();
        ModelsToDto modelsToDto=new ModelsToDto();
        mapDto= modelsToDto.fieldModelToMapDto(fieldInput);
        mapCoordinatesDTOs=new ArrayList<>();
        mapMarkersDTOs =new ArrayList<>();
        mapCoordinatesDTOs=modelsToDto.fieldModelToCoordinatesDto(fieldInput);
        mapMarkersDTOs= modelsToDto.fieldModelToMarkerDto(fieldInput);
        //TODO change save uuid to actual user and have it passed from activity
        Call<MapDTO> callMapAsync=service.saveMap("user3", mapDto);
        Call<List<MapCoordinatesDTO>> callCoordinatesAsync = service.saveCoordinatesOfMap(fieldInput.getUniqueFieldID(), mapCoordinatesDTOs);
        Call<List<MarkerDTO>> callMarkersAsync = service.saveMarkersOfMap(fieldInput.getUniqueFieldID(), mapMarkersDTOs);
        callMapAsync.enqueue(new Callback<MapDTO>() {
            @Override
            public void onResponse(Call<MapDTO> call, Response<MapDTO> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(mctx, "Map saved successfully", Toast.LENGTH_SHORT).show();
                    callCoordinatesAsync.enqueue(new Callback<List<MapCoordinatesDTO>>() {
                        @Override
                        public void onResponse(Call<List<MapCoordinatesDTO>> call, Response<List<MapCoordinatesDTO>> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(mctx, "Coordinates saved successfully", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<MapCoordinatesDTO>> call, Throwable t) {
                            Toast.makeText(mctx, "Coordinates save failed", Toast.LENGTH_SHORT).show();
                        }

                    });
                    callMarkersAsync.enqueue(new Callback<List<MarkerDTO>>() {
                        @Override
                        public void onResponse(Call<List<MarkerDTO>> call, Response<List<MarkerDTO>> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(mctx, "Markers saved successfully", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<List<MarkerDTO>> call, Throwable t) {
                            Toast.makeText(mctx, "Markers save failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<MapDTO> call, Throwable t) {
                Toast.makeText(mctx, "Map save failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public interface FieldModelLoadedListener{
        void loadField(FieldModel outputField);

    }
}


