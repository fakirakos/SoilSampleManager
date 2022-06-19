package com.example.MenusAndUtilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.RestfulWebService.MapDTO;
import com.example.soilsamplemanager.R;

import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.MyViewHolder>{

    private LayoutInflater inflater;
    private List<MapDTO> mapsChoice;
    private AdapterListener listener;

    public AdapterRecycler(Context ctx, List<MapDTO> mapsChoice){

        inflater = LayoutInflater.from(ctx);
        this.mapsChoice=mapsChoice;
        listener= (AdapterListener) ctx;
    }



    @NonNull
    @Override
    public AdapterRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_dialog_card_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterRecycler.MyViewHolder holder, int position) {

        holder.name.setText(mapsChoice.get(position).getMapName());

    }

    @Override
    public int getItemCount() {

        return mapsChoice.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        protected TextView name;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.name);

            itemView.setOnClickListener(v -> {
                //TODO return map choice to retrofit for coordinates and markers call
                    System.out.println("You have selected : "+mapsChoice.get(getAdapterPosition()).getMapName());
                    MapDTO chosenMap= mapsChoice.get(getAdapterPosition());
                    listener.loadChosenMap(chosenMap);
                    MapChoiceDialog.dialog.dismiss();
            });

        }


    }
    public interface AdapterListener{
          void loadChosenMap(MapDTO chosenMap);
    }
}
