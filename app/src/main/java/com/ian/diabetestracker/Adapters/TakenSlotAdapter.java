package com.ian.diabetestracker.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ian.diabetestracker.Models.SlotModel;
import com.ian.diabetestracker.R;

import java.util.List;

import io.paperdb.Paper;


public class TakenSlotAdapter extends RecyclerView.Adapter<TakenSlotAdapter.Viewholder> {

        List<SlotModel> mSlot;
        Context context;

        public TakenSlotAdapter(List<SlotModel> mSlot, Context context) {
            this.mSlot = mSlot;
            this.context = context;
        }

        @NonNull
        @Override
        public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.taken_slot_item,parent,false);

            Paper.init(context);
            return new Viewholder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull final Viewholder holder, int position) {

            SlotModel models = mSlot.get(position);

            holder.taken_slot_name.setText(models.getStitle());
            holder.taken_slot_time.setText(models.getStime());
//            holder.bookId.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    final String topname = holder.taken_slot_name.getText().toString();
//                    String ttime = holder.taken_slot_time.getText().toString();
//
//                    Paper.book().write(TOPIC,topname);
//                    Paper.book().write(TOPICTIME,ttime);
//
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST, UPDATE_SLOTS, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            String message = "You booked "+topname+" please keep time";
//                            AlertDialog.Builder alert = new AlertDialog.Builder(context);
//                            alert.setTitle("Diabetes Tracker");
//                            alert.setMessage(message);
//                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent intent = new Intent(context, MainActivity.class);
//                                    context.startActivity(intent);
//                                }
//                            });
//                            alert.create().show();
//                        }
//                    },
//                            new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//
//                                }
//                            }){
//                        @Override
//                        protected Map<String, String> getParams() throws AuthFailureError {
//                            Map<String,String> map = new HashMap<>();
//                            map.put("slot_name",topname);
//                            return map;
//                        }
//                    };
//                    RequestQueue requestQueue = Volley.newRequestQueue(context);
//                    requestQueue.add(stringRequest);
//                }
//            });

        }

        @Override
        public int getItemCount() {
            return mSlot.size();
        }

        class Viewholder extends RecyclerView.ViewHolder{
            TextView taken_slot_name,taken_slot_time;

            public Viewholder(@NonNull View itemView) {
                super(itemView);
                taken_slot_name = itemView.findViewById(R.id.taken_slot_client);
                taken_slot_time = itemView.findViewById(R.id.taken_slot_time);
            }
        }

}
