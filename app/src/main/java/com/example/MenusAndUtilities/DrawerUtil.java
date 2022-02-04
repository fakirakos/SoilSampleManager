package com.example.MenusAndUtilities;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.MapActivities.MapActivity;
import com.example.soilsamplemanager.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.ExpandableDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class DrawerUtil {
    public static void getDrawer(final MapActivity activity, Toolbar toolbar) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        ExpandableDrawerItem drawerDrawMap = new ExpandableDrawerItem().withIdentifier(1)
                .withName(R.string.mapDrawStart).withSubItems(
                        new PrimaryDrawerItem().withName(R.string.mapCreateField).withLevel(2).withIdentifier(101),
                        new PrimaryDrawerItem().withName(R.string.mapDesignateCrop).withLevel(2).withIdentifier(102),
                        new PrimaryDrawerItem().withName(R.string.mapCreateMarker).withLevel(2).withIdentifier(103)
                );
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(2).withName(R.string.layerVisibility);

        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(3)
                .withName(R.string.settings);
        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(4)
                .withName(R.string.about);
        SecondaryDrawerItem drawerItemHelp = new SecondaryDrawerItem().withIdentifier(5)
                .withName(R.string.help);





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
                        drawerDrawMap,
                        drawerItemManagePlayersTournaments,
                        new DividerDrawerItem(),
                        drawerItemAbout,
                        drawerItemSettings,
                        drawerItemHelp
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
                        if (drawerItem.getIdentifier()==103){
                            if(activity.isLocationWithinField(activity.getMyLocation())) {
                                activity.openDialog("Marker");
                            }
                            else{
                                Toast.makeText(activity, "The marker must be within the bounds of the field", Toast.LENGTH_SHORT);
                            }
                        }
                        return false;
                    }
                })

                .build();
    }
}
