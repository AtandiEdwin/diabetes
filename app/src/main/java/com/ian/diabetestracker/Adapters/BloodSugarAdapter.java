package com.ian.diabetestracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ian.diabetestracker.Models.BloodSugarModel;
import com.ian.diabetestracker.R;

import java.util.List;

public class BloodSugarAdapter  extends RecyclerView.Adapter<BloodSugarAdapter.BloodSugarViewHolder> {

    Context context;
    List<BloodSugarModel> sugarList;

    public BloodSugarAdapter(Context context, List<BloodSugarModel> sugarList) {
        this.context = context;
        this.sugarList = sugarList;
    }

    public BloodSugarAdapter() {
    }

    @NonNull
    @Override
    public BloodSugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.blood_sugar_item,null);
        return new BloodSugarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodSugarViewHolder holder, int position) {

        BloodSugarModel model = sugarList.get(position);
        holder.time.setText(model.getTime());;
        holder.notes.setText(model.getNotes());
        holder.unitOfMeasure.setText(model.getUnitofMeasure());
        holder.whenMeasured.setText(model.getWhenMeasured());
        holder.date.setText(model.getDate());
//        holder.tags.setText(model.getTags());

    }

    @Override
    public int getItemCount() {
        return sugarList.size();
    }

    class BloodSugarViewHolder extends RecyclerView.ViewHolder{

        TextView whenMeasured,unitOfMeasure,notes,tags,date,time;
        public BloodSugarViewHolder(@NonNull View itemView) {
            super(itemView);

            whenMeasured = itemView.findViewById(R.id.whenMeasured);
            unitOfMeasure = itemView.findViewById(R.id.unitOfMeasure);
            notes = itemView.findViewById(R.id.sugarnotes);
//            tags = itemView.findViewById(R.id.sugartags);
            date = itemView.findViewById(R.id.sugardateMeasured);
            time = itemView.findViewById(R.id.sugartimeMeasured);


        }
    }
}
