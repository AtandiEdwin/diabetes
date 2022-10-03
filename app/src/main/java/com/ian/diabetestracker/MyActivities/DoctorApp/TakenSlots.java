package com.ian.diabetestracker.MyActivities.DoctorApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ian.diabetestracker.Adapters.DocSlotAdapter;
import com.ian.diabetestracker.Adapters.SlotAdapter;
import com.ian.diabetestracker.Adapters.TakenSlotAdapter;
import com.ian.diabetestracker.Models.SlotModel;
import com.ian.diabetestracker.MyActivities.PatientApp.SlotsActivity;
import com.ian.diabetestracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.ian.diabetestracker.Constants.Links.DOC_SLOTS;

public class TakenSlots extends AppCompatActivity {
    RecyclerView doc_Slots;
    List<SlotModel> mslot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_slots);

        doc_Slots=findViewById(R.id.doc_Slots);
        mslot = new ArrayList<>();

        LoadSlots();
    }

    private void LoadSlots() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, DOC_SLOTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject =jsonArray.getJSONObject(i);

                            String topic =  jsonObject.getString("slot_name");
                            String time = jsonObject.getString("slot_time");

                            SlotModel model = new SlotModel(topic,time);

                            mslot.add(model);
                        }

                    TakenSlotAdapter adapter = new TakenSlotAdapter(mslot, TakenSlots.this);
                    doc_Slots.setAdapter(adapter);
                    doc_Slots.setLayoutManager(new LinearLayoutManager(TakenSlots.this));
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
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
//
//        TakenSlotAdapter adapter = new TakenSlotAdapter();
//        doc_Slots.setAdapter(adapter);
    }
}
