package com.example.MenusAndUtilities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.soilsamplemanager.R;

public class MarkerDialog extends DialogFragment {
    private DialogHolderListener listener;
    private EditText editTextPH;
    private EditText editTextEC;
    private EditText editTextNitrogen;
    private EditText editTextPotassium;
    private EditText editTextPhosphorus;
    private EditText editTextCalcium;
    private EditText editTextMagnesium;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.marker_save_dialog_layout, null);
        builder.setView(dialogView)
                .setTitle("Save Map")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String markerPH=editTextPH.getText().toString().trim();
                        String markerEC=editTextEC.getText().toString().trim();
                        String markerNitrogen=editTextNitrogen.getText().toString().trim();
                        String markerPotassium=editTextPotassium.getText().toString().trim();
                        String markerPhosphorus=editTextPhosphorus.getText().toString().trim();
                        String markerCalcium=editTextCalcium.getText().toString().trim();
                        String markerMagnesium=editTextMagnesium.getText().toString().trim();

                        listener.markerMaker(markerPH, markerEC, markerNitrogen, markerPotassium, markerPhosphorus, markerCalcium, markerMagnesium);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MarkerDialog.this.getDialog().cancel();
                    }
                });

        editTextPH= dialogView.findViewById(R.id.edit_PH);
        editTextEC=dialogView.findViewById(R.id.edit_EC);
        editTextNitrogen=dialogView.findViewById(R.id.edit_Nitrogen);
        editTextPotassium=dialogView.findViewById(R.id.edit_Potassium);
        editTextPhosphorus=dialogView.findViewById(R.id.edit_Phosphorus);
        editTextCalcium=dialogView.findViewById(R.id.edit_Calcium);
        editTextMagnesium=dialogView.findViewById(R.id.edit_Magnesium);
        return builder.create();

    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        try {
            listener= (DialogHolderListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "must implement DialogHolderListener");
        }
    }

    public interface DialogHolderListener{
        void markerMaker(String markerPH, String markerEC, String markerNitrogen, String markerPotassium, String markerPhosphorus, String markerCalcium, String markerMagnesium );
    }


}
