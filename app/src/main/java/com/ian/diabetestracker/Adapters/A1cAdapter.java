package com.ian.diabetestracker.Adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ian.diabetestracker.Models.A1cModel;
import com.ian.diabetestracker.Models.WeightModel;
import com.ian.diabetestracker.R;

import java.util.List;

public class A1cAdapter  extends RecyclerView.Adapter<A1cAdapter.A1cView>{

    Context context;
    List<A1cModel> mA1c;

    public A1cAdapter(Context context, List<A1cModel> mA1c) {
        this.context = context;
        this.mA1c = mA1c;
    }

    public A1cAdapter() {
    }

    @NonNull
    @Override
    public A1cView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.a1c_item,null);
        return new A1cView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull A1cView holder, int position) {

        A1cModel model = mA1c.get(position);
        holder.A1c_concentration.setText(model.getA1c_concentration());
        holder.A1c_date.setText(model.getA1c_date());
        holder.A1c_notes.setText(model.getA1c_notes());
        holder.A1c_time.setText(model.getA1c_time());
        holder.A1c_uom.setText(model.getA1c_uom());

    }

    @Override
    public int getItemCount() {
        return mA1c.size();
    }

    class A1cView extends RecyclerView.ViewHolder{
        TextView A1c_concentration,A1c_uom,A1c_date,A1c_time,A1c_notes;

        public A1cView(@NonNull View itemView) {
            super(itemView);
            A1c_concentration = itemView.findViewById(R.id.aConcId);
            A1c_uom = itemView.findViewById(R.id.aMtypeId);
            A1c_date =itemView.findViewById(R.id.adateMeasured);
            A1c_time = itemView.findViewById(R.id.atimeMeasured);
            A1c_notes = itemView.findViewById(R.id.anotes);
        }
    }
}

