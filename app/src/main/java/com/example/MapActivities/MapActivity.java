package com.example.MapActivities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.location.LocationDataSource;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.example.GraphicBuilders.BorderBuilder;
import com.example.GraphicBuilders.FieldBuilder;
import com.example.MenusAndUtilities.DrawerUtil;
import com.example.MenusAndUtilities.MapNameDialog;
import com.example.MenusAndUtilities.MarkerDialog;
import com.example.Models.FieldModel;
import com.example.Models.MarkerModel;
import com.example.soilsamplemanager.R;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


public class MapActivity extends AppCompatActivity implements MapNameDialog.DialogHolderListener {

    private static PointCollection borderPoints;
    private static GraphicsOverlay tempOverlay;
    private static GraphicsOverlay fieldOverlay;
    private static GraphicsOverlay cropOverlay;
    private static GraphicsOverlay markerOverlay;
    private MapView mMapView;
    private LocationDisplay mLocationDisplay;
    private double longitude;
    private double latitude;
    private static boolean drawingStatus;
    private static boolean drawingInitiated;
    private static Point myLocation;
    private static Point markerClickLocation;
    private Button buttonOk;
    private Button buttonCancel;
    BorderBuilder myBuilder;
    FieldBuilder myPolygonBuilder;
    FieldModel currentMapModel;
    private static boolean fieldDialog;
    private static String whichDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        borderPoints = new PointCollection(SpatialReferences.getWgs84());
        drawingStatus = false;
        drawingInitiated = false;
        fieldDialog = false;
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        buttonOk = findViewById(R.id.buttonOk);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO fix the if statement to use whichDialog instead of a boolean to work with all 3 states
                if (fieldDialog) {
                    openDialog("Field");
                } else {
                    //TODO Crop Generation (check all borderpoints against field(?) then create inner polygon
                }
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                cancelDrawing();

                                            }
                                        }
        );
        setSupportActionBar(myToolbar);
        DrawerUtil.getDrawer(this, myToolbar);
        tempOverlay = new GraphicsOverlay();
        fieldOverlay = new GraphicsOverlay();
        cropOverlay = new GraphicsOverlay();
        markerOverlay = new GraphicsOverlay();
        // inflate MapView from layout
        mMapView = findViewById(R.id.mapView);
        // create a map with the BasemapType topographic
        ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 41.08499, 23.54757, 16);
        // set the map to be displayed in this view
        mMapView.setMap(map);
        setupLocationDisplay();
        mMapView.getGraphicsOverlays().add(tempOverlay);
        mMapView.getGraphicsOverlays().add(fieldOverlay);
        mMapView.getGraphicsOverlays().add(cropOverlay);
        mMapView.getGraphicsOverlays().add(markerOverlay);
        mLocationDisplay.addLocationChangedListener(locationChangedEvent -> {
            LocationDataSource.Location location = mLocationDisplay.getLocation();
            if (location != null) {
                myLocation = location.getPosition();
                if (isDrawingInitiated()) {
                    startDrawing(myLocation);
                }
                //assignLocation(myLocation);
                else if (isDrawingStatus()) {
                    Log.v("drawingstatuschange", Boolean.toString(drawingStatus));
                    drawingTool(myLocation);
                    Log.v("drawingstatus exp true", "drawing stat exp true" + drawingStatus);
                    Log.v("drawinginit exp true", "drawing init exp true" + drawingInitiated);

                }
            }
        });
        MapViewTouchListener mMapViewTouchListener = new MapViewTouchListener(this, mMapView);
        mMapView.setOnTouchListener(mMapViewTouchListener);


    }

    private void cancelDrawing() {
        drawingStatus = false;
        drawingInitiated = false;
        borderPoints.clear();
        myBuilder = null;
        buttonCancel.setVisibility(View.GONE);
        buttonOk.setVisibility(View.GONE);
        tempOverlay.getGraphics().clear();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.dispose();
    }

    private void setupLocationDisplay() {
        mLocationDisplay = mMapView.getLocationDisplay();
        mLocationDisplay.addDataSourceStatusChangedListener(dataSourceStatusChangedEvent -> {

            // If LocationDisplay started OK or no error is reported, then continue.
            if (dataSourceStatusChangedEvent.isStarted() || dataSourceStatusChangedEvent.getError() == null) {
                return;
            }

            int requestPermissionsCode = 2;
            String[] requestPermissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

            // If an error is found, handle the failure to start.
            // Check permissions to see if failure may be due to lack of permissions.
            if (!(ContextCompat.checkSelfPermission(MapActivity.this, requestPermissions[0]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(MapActivity.this, requestPermissions[1]) == PackageManager.PERMISSION_GRANTED)) {

                // If permissions are not already granted, request permission from the user.
                ActivityCompat.requestPermissions(MapActivity.this, requestPermissions, requestPermissionsCode);
            } else {

                // Report other unknown failure types to the user - for example, location services may not
                // be enabled on the device.
                String message = String.format("Error in DataSourceStatusChangedListener: %s", dataSourceStatusChangedEvent
                        .getSource().getLocationDataSource().getError().getMessage());
                Toast.makeText(MapActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.COMPASS_NAVIGATION);
        mLocationDisplay.startAsync();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            // Location permission was granted. This would have been triggered in response to failing to start the
            // LocationDisplay, so try starting this again.
            mLocationDisplay.startAsync();
        } else {

            // If permission was denied, show toast to inform user what was chosen. If LocationDisplay is started again,
            // request permission UX will be shown again, option should be shown to allow never showing the UX again.
            // Alternative would be to disable functionality so request is not shown again.
            Toast.makeText(MapActivity.this, getResources().getString(R.string.location_permission_denied), Toast.LENGTH_SHORT).show();
        }
    }

    public void openDialog(String whichDialog) {
        switch (whichDialog) {
            case "Field":
                MapNameDialog mapNameDialog = new MapNameDialog();
                mapNameDialog.show(getSupportFragmentManager(), "test dialog");
                break;
            case "Marker":
                markerClickLocation=myLocation;
                MarkerDialog markerDialog= new MarkerDialog();
                markerDialog.show(getSupportFragmentManager(), "marker dialog");
                break;
        }
    }

    private Point assignLocation(Point point) {
        myLocation = point;
        Log.i("MainActivity", "My device location is " + point.getX() + " " + point.getY());
        Log.v("", Boolean.toString(drawingStatus));
        Log.v("", Boolean.toString(drawingInitiated));
        //    You could also assign your Longitude and Latitude values here
        return myLocation;
    }

    private void drawingTool(Point myLocation) {
        myBuilder.addPointToEnd(myLocation);
        borderPoints.add(myLocation);
        myBuilder.drawLatestGeometry(tempOverlay);
    }

    public void startDrawing(Point myLocation) {
        Log.v("drawinginit", "drawing init" + drawingInitiated);
        borderPoints.add(myLocation);
        myBuilder = new BorderBuilder(getBorderPoints());
        myBuilder.drawLatestGeometry(tempOverlay);
        mMapView.getGraphicsOverlays().get(0).getGraphics().add(myBuilder.getTemporaryGraphic());
        setDrawingInitiated(false);
        setDrawingStatus(true);
        whichDialog="Field";
        fieldDialog=true;
        buttonOk.setVisibility(View.VISIBLE);
        buttonCancel.setVisibility(View.VISIBLE);

    }

    public static boolean isDrawingStatus() {

        return drawingStatus;
    }

    public static void setDrawingStatus(boolean bool) {

        drawingStatus = bool;
    }

    public static boolean isDrawingInitiated() {

        return drawingInitiated;
    }

    public static void setDrawingInitiated(boolean drawingInitiated) {
        MapActivity.drawingInitiated = drawingInitiated;
    }

    public static PointCollection getBorderPoints() {

        return borderPoints;
    }


    @Override
    public void applyField(String mapName) {
        String uniqueFieldId = UUID.randomUUID().toString();
        currentMapModel = new FieldModel(getBorderPoints(), uniqueFieldId, mapName);
        myPolygonBuilder = new FieldBuilder(getBorderPoints());
        tempOverlay.getGraphics().add(myPolygonBuilder.getTempPolygonGraphic());
        drawingInitiated = false;
        drawingStatus = false;
        myBuilder = null;
        fieldDialog=false;
        buttonCancel.setVisibility(View.GONE);
        buttonOk.setVisibility(View.GONE);

    }


    //Creates and adds the point marker to the map
    //TODO identify point on click and create callout with dialog information
    public void markerMaker(String markerPH, String markerEC, String markerNitrogen, String markerPotassium, String markerPhosphorus, String markerCalcium, String markerMagnesium) {
        String uniqueMarkerId = UUID.randomUUID().toString();
        currentMapModel.addMarker(new MarkerModel(markerClickLocation, uniqueMarkerId, markerPH, markerEC, markerNitrogen, markerPotassium, markerPhosphorus, markerCalcium, markerMagnesium));
        SimpleMarkerSymbol myMarker = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.RED, 10);
        Graphic markerGraphic = new Graphic(markerClickLocation, myMarker);
        markerOverlay.getGraphics().add(markerGraphic);
    }

    public static boolean isFieldDialog() {
        return fieldDialog;
    }

    public static void setFieldDialog(boolean fieldDialog) {
        MapActivity.fieldDialog = fieldDialog;
    }

    class MapViewTouchListener extends DefaultMapViewOnTouchListener {

        /**
         * Constructs a DefaultMapViewOnTouchListener with the specified Context and MapView.
         *
         * @param context the context from which this is being created
         * @param mapView the MapView with which to interact
         */
        public MapViewTouchListener(Context context, MapView mapView) {
            super(context, mapView);
        }

        /**
         * Override the onSingleTapConfirmed gesture to handle tapping on the MapView
         * and detected if the Graphic was selected.
         *
         * @param e the motion event
         * @return true if the listener has consumed the event; false otherwise
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // get the screen point where user tapped
            android.graphics.Point screenPoint = new android.graphics.Point((int) e.getX(), (int) e.getY());

            // identify graphics on the graphics overlay
            final ListenableFuture<IdentifyGraphicsOverlayResult> identifyGraphic = mMapView.identifyGraphicsOverlayAsync(markerOverlay, screenPoint, 10.0, false, 2);

            identifyGraphic.addDoneListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        IdentifyGraphicsOverlayResult grOverlayResult = identifyGraphic.get();
                        // get the list of graphics returned by identify graphic overlay
                        List<Graphic> graphic = grOverlayResult.getGraphics();
                        // get size of list in results
                        int identifyResultSize = graphic.size();
                        if (!graphic.isEmpty()) {
                            if (identifyResultSize > 1) {
                                Toast.makeText(getApplicationContext(), "Touched multiple markers, Zoom the map in and try again", Toast.LENGTH_SHORT).show();
                            } else {
                                Point temp = (Point) graphic.get(0).getGeometry();
                                Point wgs84Point = (Point) GeometryEngine.project(temp, SpatialReferences.getWgs84());
                                for (MarkerModel tempMarker : currentMapModel.getMarkerList()) {
                                    if(tempMarker.getMarkerLocation().equals(wgs84Point)){
                                        Toast.makeText(getApplicationContext(), tempMarker.getUniqueMarkerId(), Toast.LENGTH_LONG);
                                    }
                                }
                            }
                        }
                    } catch (InterruptedException | ExecutionException ie) {
                        ie.printStackTrace();
                    }

                }
            });

            return super.onSingleTapConfirmed(e);
        }
    }

    public boolean isFieldModelInitialized(){
        if(currentMapModel!=null)
            return true;
        else
            return false;
    }

    public boolean isLocationWithinField(Point myLocation){
        boolean isContain = GeometryEngine.contains(myPolygonBuilder.getFieldPolygon(), myLocation);
        return isContain;
    }

    public static Point getMyLocation() {
        return myLocation;
    }
}
