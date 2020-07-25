package com.ian.diabetestracker.MyActivities.PatientApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.toolbox.Volley;
import com.ian.diabetestracker.Adapters.SlotAdapter;
import com.ian.diabetestracker.MainActivity;
import com.ian.diabetestracker.Models.SlotModel;
import com.ian.diabetestracker.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

import static com.ian.diabetestracker.Constants.Links.LOAD_SLOTS;
import static com.ian.diabetestracker.Constants.PaperCommons.TOPIC;
import static com.ian.diabetestracker.Constants.PaperCommons.TOPICTIME;

public class SlotsActivity extends AppCompatActivity {
    RecyclerView slotRecycler;
    List<SlotModel> mslot;
    LinearLayout slot_note_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slots);

        Paper.init(SlotsActivity.this);
        TextView topicsID = findViewById(R.id.topicsID);
        TextView topicTimeID =findViewById(R.id.topicTimeID);
        final LinearLayout slot = findViewById(R.id.slot);
        Button yesSlot  =findViewById(R.id.yesSlot);
        Button noSlot = findViewById(R.id.noSlot);
        slot_note_id = findViewById(R.id.slot_note_id);

        mslot = new ArrayList<>();
        slotRecycler = findViewById(R.id.slotRecycler);
        slotRecycler.setHasFixedSize(true);

        String topic = Paper.book().read(TOPIC);
        String time = Paper.book().read(TOPICTIME);

        if(topic!=null && time !=null){
            if(!topic.isEmpty()&&!time.isEmpty()){
                    slotRecycler.setVisibility(View.GONE);
                    slot.setVisibility(View.VISIBLE);
                    topicsID.setText(topic);
                    topicTimeID.setText(time);
            }
        }

        else{
            slot.setVisibility(View.GONE);
            slotRecycler.setVisibility(View.VISIBLE);
            loadslots();
        }


        yesSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                loadslots();
                slot.setVisibility(View.GONE);
                slotRecycler.setVisibility(View.VISIBLE);
            }
        });

        noSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SlotsActivity.this);
                alert.setTitle("Diabetes Tracker");
                alert.setMessage("Please attend to it,,but if the slot time has expired press yes to book a new slot");
                alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(SlotsActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                alert.create().show();
                Toast.makeText(SlotsActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SlotsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void loadslots(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LOAD_SLOTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if(response!=null){
                        slot_note_id.setVisibility(View.VISIBLE);

                    }
                    else{
                        slot_note_id.setVisibility(View.GONE);
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject =jsonArray.getJSONObject(i);

                            String topic =  jsonObject.getString("slot_name");
                            String time = jsonObject.getString("slot_time");

                            SlotModel model = new SlotModel(topic,time);
                            mslot.add(model);
                        }

                    }

                    SlotAdapter adapter = new SlotAdapter(mslot,SlotsActivity.this);
                    slotRecycler.setAdapter(adapter);
                    slotRecycler.setLayoutManager(new LinearLayoutManager(SlotsActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(SlotsActivity.this);
        requestQueue.add(stringRequest);
    }
}
