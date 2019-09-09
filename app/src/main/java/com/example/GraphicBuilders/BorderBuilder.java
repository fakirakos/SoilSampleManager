package com.example.GraphicBuilders;

import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.PolylineBuilder;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;

public class BorderBuilder   {
    PolylineBuilder polylineBuilder = null;
    Graphic temporaryGraphic = null;


    // Builder held as member variable
    public BorderBuilder (Feature roadFeature) {
        // Set initial state of the builder based on an existing geometry
        polylineBuilder = new PolylineBuilder((Polyline) roadFeature.getGeometry());
        // Set up a temporary graphic to draw the geometry
        SimpleLineSymbol roadSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFFFF0000, 1f);
        temporaryGraphic = new Graphic(polylineBuilder.toGeometry(), roadSymbol);
    }


    public void addPointToEnd(Point newEndPoint) {
        // Change the geometry
        polylineBuilder.getParts().get(0).addPoint(newEndPoint);
    }


    public Polyline getCurrentGeometry() {
        // Get latest state of the geometry
        return polylineBuilder.toGeometry();
    }


    public void drawLatestGeometry(GraphicsOverlay editorOverlay) {
        // Update the user's view of the geometry
        temporaryGraphic.setGeometry(getCurrentGeometry());
    }

    public Graphic getTemporaryGraphic(Graphic temporaryGraphic){
        return temporaryGraphic;
    }
}
