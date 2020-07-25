package com.ian.diabetestracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ian.diabetestracker.Models.Messages;
import com.ian.diabetestracker.R;

import org.w3c.dom.Text;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    Context context;
    List<Messages> mMessage;
    int MSG_RIGHT =  0,MSG_LEFT = 1;
    Messages messages;
    public MessageAdapter() {
    }

    public MessageAdapter(Context context, List<Messages> mMessage) {
        this.context = context;
        this.mMessage = mMessage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_RIGHT){
            View view = LayoutInflater.from(context).inflate(R.layout.right_chat,parent,false);
            return new MessageAdapter.ViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.left_chat,parent,false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        messages = mMessage.get(position);
        holder.one_message.setText(messages.getMessage());
    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mMessage.get(position).getSender().equals("Patient")){
            return MSG_RIGHT;
        }
        else{
            return MSG_LEFT;
        }
    }

    public void deleteItem(int pos) {

        mMessage.remove(pos);
        notifyItemRemoved(pos);
        Toast.makeText(context, "message deleted" , Toast.LENGTH_SHORT).show();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView one_message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            one_message = itemView.findViewById(R.id.one_message);
        }
    }
}
