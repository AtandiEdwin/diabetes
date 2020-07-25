package com.ian.diabetestracker.MyActivities.DoctorApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ian.diabetestracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ian.diabetestracker.Constants.Links.ADD_SLOTS;
import static com.ian.diabetestracker.Constants.Links.FETCH_SLOTS;

public class SlotSet extends AppCompatActivity {

    List<String> mslot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_set);

        Button btnSlot = findViewById(R.id.btnSlot);
        ImageButton getTime = findViewById(R.id.getTime);
        TextView setTime = findViewById(R.id.setTime);
        final EditText editi =findViewById(R.id.editi);
        final Spinner spinner= findViewById(R.id.spinner);
        mslot = new ArrayList<>();


        final ArrayAdapter<String> adapter = new ArrayAdapter<>(SlotSet.this, android.R.layout.simple_spinner_dropdown_item, mslot);
        spinner.setAdapter(adapter);

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, FETCH_SLOTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;

                try {

                    jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String rn = jsonObject.getString("slot_name");
                        mslot.add(rn);
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(SlotSet.this);
        requestQueue.add(stringRequest);

        btnSlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinner.getSelectedItem() == null) {

                    Toast.makeText(SlotSet.this, "no server connection", Toast.LENGTH_SHORT).show();
                } else {
                    final String sname = spinner.getSelectedItem().toString();
                    final String ttme = editi.getText().toString();
                    if (ttme.isEmpty()) {

                        Toast.makeText(SlotSet.this, "time cannot be null", Toast.LENGTH_SHORT).show();
                    } else{
                        final StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_SLOTS, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(SlotSet.this, response, Toast.LENGTH_SHORT).show();
                            }
                        },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                    }
                                }
                        ) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> map = new HashMap<>();
                                map.put("slot_name", sname);
                                map.put("slot_time", ttme);
                                return map;
                            }
                        };
                    RequestQueue requestQueue = Volley.newRequestQueue(SlotSet.this);
                    requestQueue.add(stringRequest);
                }
            }
            }
        });



    }
}
