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
import com.ian.diabetestracker.Adapters.BloodSugarAdapter;
import com.ian.diabetestracker.MainActivity;
import com.ian.diabetestracker.Models.BloodSugarModel;
import com.ian.diabetestracker.ModficationActivity.BloodSugarModification;
import com.ian.diabetestracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.ian.diabetestracker.Constants.Links.FETCH_SUGAR_DETAILS;

public class BloodSugarActivity extends AppCompatActivity {

    RecyclerView sugarRecycler;
    List<BloodSugarModel> mSugarList;
    TextView totalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Blood Sugar");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mSugarList = new ArrayList<>();
        sugarRecycler = findViewById(R.id.bloodSugarRecyclerId);
        sugarRecycler.setHasFixedSize(true);

        totalValue = findViewById(R.id.totalValue);


        StringRequest stringRequest = new StringRequest(Request.Method.GET,FETCH_SUGAR_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String whenMeasured = jsonObject.getString("when_measured");
                        String unitofMeasure = jsonObject.getString("unit_of_measure");
                        String notes = jsonObject.getString("sugar_notes");
                        String date = jsonObject.getString("sugar_date");
                        String time = jsonObject.getString("sugar_time");

                        BloodSugarModel model = new BloodSugarModel(whenMeasured,unitofMeasure,notes,date,time);
                        mSugarList.add(model);
                    }

                    BloodSugarAdapter adapter = new BloodSugarAdapter(BloodSugarActivity.this,mSugarList);
                    sugarRecycler.setAdapter(adapter);
                    sugarRecycler.setLayoutManager(new LinearLayoutManager(BloodSugarActivity.this));

                    totalValue.setText(String.valueOf(mSugarList.size()));

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
                startActivity(new Intent(BloodSugarActivity.this, BloodSugarModification.class));
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BloodSugarActivity.this, MainActivity.class));
        finish();
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
