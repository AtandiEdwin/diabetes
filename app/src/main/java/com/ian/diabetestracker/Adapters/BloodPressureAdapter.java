package com.ian.diabetestracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ian.diabetestracker.Models.BloodPressureModel;
import com.ian.diabetestracker.R;

import java.util.List;

public class BloodPressureAdapter extends RecyclerView.Adapter<BloodPressureAdapter.BloodPressureViewHolder>{

    List<BloodPressureModel> pressureModels;
    Context context;

    public BloodPressureAdapter(List<BloodPressureModel> pressureModels, Context context) {
        this.pressureModels = pressureModels;
        this.context = context;
    }

    public BloodPressureAdapter() {
    }

    @NonNull
    @Override
    public BloodPressureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.blood_pressure_item,null);
        return new BloodPressureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodPressureViewHolder holder, int position) {

        BloodPressureModel model = pressureModels.get(position);
        holder.note.setText(model.getNote());
        holder.diastolic.setText(model.getDiastolic());
        holder.systolic.setText(model.getSystolic());
        holder.arm.setText(model.getArm());
        holder.date.setText(model.getDate());
        holder.pulse.setText(model.getPulse());
//        holder.tag.setText(model.getTag());
        holder.time.setText(model.getTime());

    }

    @Override
    public int getItemCount() {
        return pressureModels.size();
    }

    class BloodPressureViewHolder extends RecyclerView.ViewHolder{

        TextView systolic,diastolic,pulse,arm,date,time,note,tag;

        public BloodPressureViewHolder(@NonNull View itemView) {
            super(itemView);
            systolic = itemView.findViewById(R.id.systolicId);
            diastolic = itemView.findViewById(R.id.diastolicId);
            pulse = itemView.findViewById(R.id.pulseId);
            arm = itemView.findViewById(R.id.armId);
            date = itemView.findViewById(R.id.pressuredateMeasured);
            time = itemView.findViewById(R.id.pressuretimeMeasured);
            note = itemView.findViewById(R.id.pressurenotes);
//            tag = itemView.findViewById(R.id.pressuretags);
        }
    }
}
