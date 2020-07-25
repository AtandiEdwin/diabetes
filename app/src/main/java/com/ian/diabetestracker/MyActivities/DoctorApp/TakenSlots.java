package com.ian.diabetestracker.MyActivities.DoctorApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ian.diabetestracker.Adapters.DocSlotAdapter;
import com.ian.diabetestracker.R;

public class TakenSlots extends AppCompatActivity {
    RecyclerView doc_Slots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken_slots);
        doc_Slots=findViewById(R.id.doc_Slots);

        LoadSlots();
    }

    private void LoadSlots() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

        DocSlotAdapter adapter = new DocSlotAdapter();
        doc_Slots.setAdapter(adapter);
    }
}
