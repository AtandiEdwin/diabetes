package com.ian.diabetestracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ian.diabetestracker.Models.WeightModel;
import com.ian.diabetestracker.R;

import java.util.List;

public class WeightAdapter  extends RecyclerView.Adapter<WeightAdapter.WeightView>{

    Context context;
    List<WeightModel> mWeight;

    public WeightAdapter(Context context, List<WeightModel> mWeight) {
        this.context = context;
        this.mWeight = mWeight;
    }

    public WeightAdapter() {
    }

    @NonNull
    @Override
    public WeightView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.weight_item,null);
        return new WeightView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeightView holder, int position) {

        WeightModel model = mWeight.get(position);
        String myWeight = model.getW_value()+" Kg";
        holder.w_value.setText(myWeight);
        holder.w_time.setText(model.getW_time());
//        holder.w_tags.setText(model.getW_tags());
        holder.w_notes.setText(model.getW_notes());
        holder.w_date.setText(model.getW_date());

    }

    @Override
    public int getItemCount() {
        return mWeight.size();
    }

    class WeightView extends RecyclerView.ViewHolder{
        TextView w_value,w_date,w_time,w_notes,w_tags;

        public WeightView(@NonNull View itemView) {
            super(itemView);
            w_date = itemView.findViewById(R.id.wdateMeasured);
            w_notes = itemView.findViewById(R.id.wnotes);
//            w_tags =itemView.findViewById(R.id.wtagId);
            w_time = itemView.findViewById(R.id.wtimeMeasured);
            w_value = itemView.findViewById(R.id.weightId);
        }
    }
}
