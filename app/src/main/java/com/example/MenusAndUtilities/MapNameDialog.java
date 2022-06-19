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

public class MapNameDialog extends DialogFragment {
        private EditText editTextMapName;
        private DialogHolderListener listener;

        public MapNameDialog() {

        }


        public static MapNameDialog newInstance(String title) {

            MapNameDialog frag = new MapNameDialog();

            Bundle args = new Bundle();

            args.putString("title", title);

            frag.setArguments(args);

            return frag;

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.map_name_dialog_layout, null);
                builder.setView(dialogView)
                        .setTitle("Save Map")
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    String mapName=editTextMapName.getText().toString();
                                    listener.applyDrawnField(mapName);
                                }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                        MapNameDialog.this.getDialog().cancel();
                                }
                        });

                editTextMapName= dialogView.findViewById(R.id.edit_MapName);
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
            void applyDrawnField(String mapName);
        }
}
