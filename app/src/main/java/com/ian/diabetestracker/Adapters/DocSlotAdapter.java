package com.ian.diabetestracker.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DocSlotAdapter extends RecyclerView.Adapter<DocSlotAdapter.DocSlotViewHolder> {
    Context context;

    public DocSlotAdapter(Context context) {
        this.context = context;
    }

    public DocSlotAdapter() {
    }

    @NonNull
    @Override
    public DocSlotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DocSlotViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DocSlotViewHolder extends RecyclerView.ViewHolder{
        public DocSlotViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
