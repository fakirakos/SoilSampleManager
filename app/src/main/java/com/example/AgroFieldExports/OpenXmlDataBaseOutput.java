package com.example.AgroFieldExports;

import com.example.soilsamplemanager.R;
import com.google.android.gms.maps.model.LatLng;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class OpenXmlDataBaseOutput {
    private OpenXmlDataBaseOutput() {

    }

    public static boolean exportDBXLS(
            FileOutputStream outputStream,
            List<Land> lands,
            List<MarkerData> markers
    ) throws IOException {
        if (outputStream == null)
            return false;

        HSSFWorkbook workbook = new HSSFWorkbook();

        //Land Sheet
        if (lands.size() > 0) {
            createLandSheetXLS(lands, workbook);
        }
        if(markers.size() > 0){
            createMarkerSheetXLS(markers, workbook);
        }
        workbook.write(outputStream);
        workbook.close();
        return true;
    }

    private static void createLandSheetXLS(List<Land> lands, HSSFWorkbook workbook) {
        Row row;
        Cell cell;
        int colNum;
        int rowNum = 0;
        List<List<LatLng>> points;
        List<LatLng> border;
        HSSFSheet sheetLand = workbook.createSheet(ExportValues.sheetLandName);


        for (Land land : lands) {
            if (land == null)
                continue;

            if (land.getData() == null)
                continue;

            row = sheetLand.createRow(rowNum++);
            colNum = 0;

            cell = row.createCell(colNum++);
            cell.setCellValue(land.getData().getId());

            cell = row.createCell(colNum++);
            cell.setCellValue(land.getData().getSnapshot());

            cell = row.createCell(colNum++);
            cell.setCellValue(land.getData().getTitle());

            cell = row.createCell(colNum++);
            cell.setCellValue(land.getData().getTags());

            cell = row.createCell(colNum++);
            cell.setCellValue(land.getData().getColor().toString());

            cell = row.createCell(colNum);
            border = new ArrayList<>(land.getData().getBorder());
            points = new ArrayList<>();
            points.add(0, border);
            cell.setCellValue(LandDataConverter.pointsPrettyPrint(points));
        }
    }

    public static void createMarkerSheetXLS(List<MarkerData> markers, HSSFWorkbook workbook){
        Row row;
        Cell cell;
        int colNum;
        int rowNum = 0;
        HSSFSheet sheetMarkers = workbook.createSheet(ExportValues.sheetMarkersName);

        for(MarkerData marker: markers){
            if (marker== null){
                continue;
            }

            row = sheetMarkers.createRow(rowNum++);
            colNum = 0;

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getId());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getId());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getUniqueMarkerId());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerPH());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerEC());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerCEC());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerPbs());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerOrganicMatter());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerNitrogen());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerIron());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerZinc());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerManganese());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerCopper());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerPotassium());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerPhosphorus());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerCalcium());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerMagnesium());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerSodium());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerSulfur());

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerPoint().longitude);

            cell = row.createCell(colNum++);
            cell.setCellValue(marker.getMarkerPoint().latitude);
        }

    }
}
