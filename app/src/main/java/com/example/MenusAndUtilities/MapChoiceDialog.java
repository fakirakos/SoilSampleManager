package com.example.MenusAndUtilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.MapActivities.MapActivity;
import com.example.RestfulWebService.MapDTO;
import com.example.soilsamplemanager.R;

import java.util.List;

public class MapChoiceDialog extends DialogFragment {

    public static Dialog dialog;
    private Button dialogBtn;
    Context mctx;
    List<MapDTO> mapsChoice;
    private View textView;
    private RecyclerView recyclerFragment;


    public MapChoiceDialog(Context context, List<MapDTO> mapsChoice) {
        this.mctx=context;
        this.mapsChoice=mapsChoice;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.recycler_fragment, null);
        textView = view.findViewById(R.id.recycler_textview);
        recyclerFragment = view.findViewById(R.id.recycler);

        // Create the layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mctx);
        recyclerFragment.setLayoutManager(layoutManager);

        // Create and set the adapter
        AdapterRecycler adapter = new AdapterRecycler(mctx, mapsChoice);
        recyclerFragment.setAdapter(adapter);

        builder.setView(view);
        builder.setTitle("Choose Map");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do whatever you want when user clicks the positive button
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create(); // return the dialog builder

    }
    public void showDialog(Context context){

        dialog = new Dialog(mctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.recycler_fragment);

        Button btndialog = dialog.findViewById(R.id.btndialog);
        btndialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        AdapterRecycler adapterRecycler = new AdapterRecycler(mctx ,mapsChoice);
        recyclerView.setAdapter(adapterRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(mctx, LinearLayoutManager.VERTICAL, false));

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
