package com.ian.diabetestracker.MyActivities.PatientApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ian.diabetestracker.Adapters.BloodPressureAdapter;
import com.ian.diabetestracker.MainActivity;
import com.ian.diabetestracker.Models.BloodPressureModel;
import com.ian.diabetestracker.ModficationActivity.BloodPressureMoodification;
import com.ian.diabetestracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.ian.diabetestracker.Constants.Links.FETCH_PRESSURE_DETAILS;

public class BloodPressureActivity extends AppCompatActivity {

    List<BloodPressureModel> mPressure;
    TextView total;
    RecyclerView pRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Blood Pressure");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mPressure = new ArrayList<>();

        total = findViewById(R.id.totalValue);

        pRecycler = findViewById(R.id.bloodPressureRecyclerId);
        pRecycler.setHasFixedSize(true);


        StringRequest stringRequest = new StringRequest(Request.Method.GET,FETCH_PRESSURE_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String systolic = jsonObject.getString("systolic");
                        String diastolic = jsonObject.getString("diastolic");
                        String pulse = jsonObject.getString("pulse");
                        String arm = jsonObject.getString("arm");
                        String notes = jsonObject.getString("p_notes");
                        String date = jsonObject.getString("p_date");
                        String time = jsonObject.getString("p_time");

                        BloodPressureModel model = new BloodPressureModel(systolic,diastolic,pulse,arm,date,time,notes);
                        mPressure.add(model);
                    }

                    BloodPressureAdapter adapter = new BloodPressureAdapter( mPressure,BloodPressureActivity.this);
                    pRecycler.setAdapter(adapter);
                    pRecycler.setLayoutManager(new LinearLayoutManager(BloodPressureActivity.this));

                    total.setText(String.valueOf( mPressure.size()));

                }


                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnadd:
                startActivity(new Intent(BloodPressureActivity.this, BloodPressureMoodification.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BloodPressureActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
