package com.ian.diabetestracker.MyActivities;

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
import com.ian.diabetestracker.Adapters.WeightAdapter;
import com.ian.diabetestracker.MainActivity;
import com.ian.diabetestracker.Models.BloodSugarModel;
import com.ian.diabetestracker.Models.WeightModel;
import com.ian.diabetestracker.ModficationActivity.BloodSugarModification;
import com.ian.diabetestracker.ModficationActivity.WeightModification;
import com.ian.diabetestracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.ian.diabetestracker.Constants.Links.FETCH_SUGAR_DETAILS;
import static com.ian.diabetestracker.Constants.Links.FETCH_WEIGHT_DETAILS;

public class WeightActivity extends AppCompatActivity {

    RecyclerView wRecycler;
    List<WeightModel> mWeight;
    TextView totalvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Weight");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mWeight = new ArrayList<>();

        wRecycler = findViewById(R.id.weightRecyclerId);
        wRecycler.setHasFixedSize(true);

        totalvalue = findViewById(R.id.totalValue);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,FETCH_WEIGHT_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String w_value = jsonObject.getString("w_value");
                        String w_date = jsonObject.getString("w_date");
                        String w_time = jsonObject.getString("w_time");
                        String w_notes = jsonObject.getString("w_notes");
//                        String w_tags = jsonObject.getString("w_tags");

                        WeightModel model = new WeightModel(w_value,w_date,w_time,w_notes);
                        mWeight.add(model);
                    }

                    WeightAdapter adapter = new WeightAdapter(WeightActivity.this,mWeight);
                    wRecycler.setAdapter(adapter);
                    wRecycler.setLayoutManager(new LinearLayoutManager(WeightActivity.this));

                    totalvalue.setText(String.valueOf(mWeight.size()));

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
                startActivity(new Intent(WeightActivity.this, WeightModification.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WeightActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
