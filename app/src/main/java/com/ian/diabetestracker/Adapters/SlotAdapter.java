package com.ian.diabetestracker.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ian.diabetestracker.MainActivity;
import com.ian.diabetestracker.Models.SlotModel;
import com.ian.diabetestracker.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.paperdb.Paper;

import static com.ian.diabetestracker.Constants.Links.UPDATE_SLOTS;
import static com.ian.diabetestracker.Constants.PaperCommons.TOPIC;
import static com.ian.diabetestracker.Constants.PaperCommons.TOPICTIME;

public class SlotAdapter extends RecyclerView.Adapter<SlotAdapter.Viewholder> {

    List<SlotModel> mSlot;
    Context context;

    public SlotAdapter(List<SlotModel> mSlot, Context context) {
        this.mSlot = mSlot;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(context);
       View v = inflater.inflate(R.layout.slot_item,parent,false);

        Paper.init(context);
       return new SlotAdapter.Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, int position) {

        SlotModel model = mSlot.get(position);

        holder.topic.setText(model.getStitle());
        holder.topicTime.setText(model.getStime());
        holder.bookId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String topname = holder.topic.getText().toString();
                String ttime = holder.topicTime.getText().toString();

                Paper.book().write(TOPIC,topname);
                Paper.book().write(TOPICTIME,ttime);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_SLOTS, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String message = "You booked "+topname+" please keep time";
                        AlertDialog.Builder alert = new AlertDialog.Builder(context);
                        alert.setTitle("Diabetes Tracker");
                        alert.setMessage(message);
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context,MainActivity.class);
                                context.startActivity(intent);
                            }
                        });
                        alert.create().show();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<>();
                        map.put("slot_name",topname);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                requestQueue.add(stringRequest);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSlot.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        TextView topic,topicTime;
        Button bookId;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic);
            topicTime = itemView.findViewById(R.id.topicTime);
            bookId = itemView.findViewById(R.id.bookId);

        }
    }
}
