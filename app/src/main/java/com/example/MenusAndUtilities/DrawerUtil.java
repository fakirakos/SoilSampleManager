package com.example.MenusAndUtilities;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.AgroFieldExports.Land;
import com.example.AgroFieldExports.LandData;
import com.example.AgroFieldExports.LandDataConverter;
import com.example.AgroFieldExports.MarkerData;
import com.example.MapActivities.MapActivity;
import com.example.Models.FieldModel;
import com.example.Models.MarkerModel;
import com.example.soilsamplemanager.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DrawerUtil {
    public static void getDrawer(MapActivity activity, Toolbar toolbar) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        ExpandableDrawerItem drawerDrawMap = new ExpandableDrawerItem().withIdentifier(1)
                .withName(R.string.mapDrawStart).withSubItems(
                        new PrimaryDrawerItem().withName(R.string.mapCreateField).withLevel(2).withIdentifier(101),
                        new PrimaryDrawerItem().withName(R.string.mockLoad).withLevel(2).withIdentifier(102),
                        new PrimaryDrawerItem().withName(R.string.mapCreateMarker).withLevel(2).withIdentifier(103),
                        new PrimaryDrawerItem().withName(R.string.mockExport).withLevel(2).withIdentifier(104),
                        new PrimaryDrawerItem().withName("Export a Mock Agrofield with markers").withLevel(2).withIdentifier(105),
                        new PrimaryDrawerItem().withName("Load A Map").withLevel(2).withIdentifier(106)
                );
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(2).withName(R.string.layerVisibility);

        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(3)
                .withName(R.string.settings);
        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(4)
                .withName(R.string.about);
        SecondaryDrawerItem drawerItemHelp = new SecondaryDrawerItem().withIdentifier(5)
                .withName(R.string.help);
        SecondaryDrawerItem drawerTestRetrofitMap = new SecondaryDrawerItem().withIdentifier(6).
                withName("Tests");





        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        drawerEmptyItem,drawerEmptyItem,drawerEmptyItem,
                        drawerEmptyItem,
                        drawerEmptyItem,
                        drawerDrawMap,
                        drawerItemManagePlayersTournaments,
                        new DividerDrawerItem(),
                        drawerItemAbout,
                        drawerItemSettings,
                        drawerItemHelp,
                        drawerTestRetrofitMap
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 101) {
                            if(!activity.isFieldModelInitialized()) {
                                if (MapActivity.isDrawingStatus() == false) {
                                    MapActivity.setDrawingInitiated(true);
                                    Log.e("warning", "drawer button pressed");
                                }
                            }
                            else{
                                Toast.makeText(activity, "You have already drawn a field", Toast.LENGTH_SHORT);
                            }
                        }
                        if (drawerItem.getIdentifier()== 102){
                            if(!activity.isFieldModelInitialized()){
                                MockMapLoader mockLoader=new MockMapLoader();
                                activity.loadField(mockLoader.getMockModel());
                            }
                        }
                        if (drawerItem.getIdentifier()==103){
                            if(activity.isLocationWithinField(activity.getMyLocation())) {
                                activity.openDialog("Marker");
                            }
                            else{
                                Toast.makeText(activity, "The marker must be within the bounds of the field", Toast.LENGTH_SHORT);
                            }
                        }
                        //TODO delete afterwards, only here for easy mock exports
                        if (drawerItem.getIdentifier()==104) {
                            if (activity.checkWritePermission()) {
                                MockMapLoader mockLoader = new MockMapLoader();
                                FieldModel modelInput=mockLoader.getMockModel();
                                LandData temp = LandDataConverter.FieldModelToLandData(modelInput);
                                List<Land> lands = new ArrayList<>();
                                List<MarkerData> tempMarkerList=new ArrayList<>();
                                lands.add(new Land(temp));
                                try {
                                    LandDataConverter.dbExportAFileFileXLS(lands, tempMarkerList);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                activity.requestWritePermission();
                            }
                        }
                        if (drawerItem.getIdentifier()==105){
                            //TODO delete Identifier 104 and change to live map model after retrofit
                            if(activity.checkWritePermission()){
                                MockMapLoader mockLoader=new MockMapLoader();
                                List<MarkerModel> mockMarkers;
                                mockMarkers=mockLoader.makeMockMarkers();
                                FieldModel modelInput= mockLoader.getMockModelWithMarkers(mockMarkers);
                                LandData temp =LandDataConverter.FieldModelToLandData(modelInput);
                                List<MarkerData> tempMarkerList=new ArrayList<>();
                                if(!modelInput.getMarkerList().isEmpty())
                                    tempMarkerList = LandDataConverter.FieldModelMarkersToMarkerData(modelInput);
                                List<Land> lands = new ArrayList<>();
                                lands.add(new Land(temp));
                                try {
                                    LandDataConverter.dbExportAFileFileXLS(lands, tempMarkerList);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            else{
                                activity.requestWritePermission();
                            }
                        }
                        if (drawerItem.getIdentifier()==6){
                              activity.loadMapChoice();
//                            MapChoiceDialog dialogChoice= new MapChoiceDialog(activity.getMapActivityContext(),mapChoices);
//                            dialogChoice.showDialog(activity.getMapActivityContext());
                        }
                        return false;
                    }
                })

                .build();
    }
}
