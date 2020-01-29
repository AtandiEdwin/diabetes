package com.ian.diabetestracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ian.diabetestracker.Models.MedicationModel;
import com.ian.diabetestracker.R;

import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedViewHolder> {

        List<MedicationModel> mMed;
        Context context;

    public MedicationAdapter(List<MedicationModel> mMed, Context context) {
        this.mMed = mMed;
        this.context = context;
    }

    public MedicationAdapter() {
    }

    @NonNull
        @Override
        public MedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.medication_item,null);
            return new MedViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MedViewHolder holder, int position) {

            MedicationModel model = mMed.get(position);
//            holder.m_tags.setText(model.getM_tags());
            holder.m_notes.setText(model.getM_notes());
            holder.m_time.setText(model.getM_time());
            holder.m_date.setText(model.getM_date());
            holder.m_uom.setText(model.getM_uom());
            holder.m_dosage.setText(model.getM_dosage());
            holder.m_value.setText(model.getM_value());

        }

        @Override
        public int getItemCount() {
            return mMed.size();
        }

        class MedViewHolder extends RecyclerView.ViewHolder{

            TextView m_value,m_dosage,m_uom,m_date,m_time,m_notes,m_tags;

            public MedViewHolder(@NonNull View itemView) {
                super(itemView);
                m_value= itemView.findViewById(R.id.medId);
                m_dosage = itemView.findViewById(R.id.dosageId);
                m_uom = itemView.findViewById(R.id.mUom);
                m_date = itemView.findViewById(R.id.mdateMeasured);
                m_time = itemView.findViewById(R.id.mtimeMeasured);
                m_notes = itemView.findViewById(R.id.mnotes);
//                m_tags = itemView.findViewById(R.id.mtagId);

            }
        }

}
