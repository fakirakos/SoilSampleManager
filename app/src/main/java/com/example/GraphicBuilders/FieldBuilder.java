package com.example.GraphicBuilders;

import android.graphics.Color;
import android.util.Log;

import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;

public class FieldBuilder {
    Polygon fieldPolygon=null;
    Graphic tempPolygonGraphic=null;

    public FieldBuilder(PointCollection points){
         fieldPolygon = new Polygon(points);

        SimpleLineSymbol outlineSymbol =new SimpleLineSymbol(
            SimpleLineSymbol.Style.SOLID,
            Color.argb(255, 0, 0, 128),1 );

        SimpleFillSymbol fillSymbol =new SimpleFillSymbol(
                    SimpleFillSymbol.Style.DIAGONAL_CROSS,
                    Color.BLUE, outlineSymbol);
        tempPolygonGraphic=new Graphic(fieldPolygon, fillSymbol);
        Log.v("polygon done","polygon done");

    }

    public Graphic getTempPolygonGraphic() {
        //get the polygon Graphic drawn in the constructor
        Log.v("return", "polygon returned");
        return tempPolygonGraphic;

    }


}

//
//TODO use for crop layer