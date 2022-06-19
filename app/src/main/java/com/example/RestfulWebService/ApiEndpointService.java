package com.example.RestfulWebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndpointService {
    //change all @ to correct API calls
    @POST("/login")
    Call<UserDTO> userLogin(@Body LoginRequest loginRequest);

    @GET("users/{uuid}/maps")
    Call<List<MapDTO>> getAllUserMaps(@Path("uuid") String uuid);

    @GET("maps/{uniquemapid}/markers")
    Call<List<MarkerDTO>> getAllMarkerByMapUuid(@Path("uniquemapid") String uniqueMapId);

    @GET("maps/{uniquemapid}/coordinates")
    Call<List<MapCoordinatesDTO>> getCoordinatesOfMap(@Path("uniquemapid") String uniqueMapId);

    @POST("users/{uuid}/addmap")
    Call<MapDTO> saveMap(@Path("uuid") String uuid, @Body MapDTO mapToSave);

    @POST("/maps/{uniquemapid}/addcoordinates")
    Call<List<MapCoordinatesDTO>> saveCoordinatesOfMap(@Path("uniquemapid") String uniqueMapId, @Body List<MapCoordinatesDTO> inputCoordinates);

    @POST("maps/{uniqueMapId}/addmarkers")
    Call<List<MarkerDTO>> saveMarkersOfMap(@Path("uniquemapid") String uniqueMapId, @Body List<MarkerDTO> inputMarkers);

}
