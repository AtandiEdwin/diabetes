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
import com.ian.diabetestracker.Adapters.MedicationAdapter;
import com.ian.diabetestracker.MainActivity;
import com.ian.diabetestracker.Models.MedicationModel;
import com.ian.diabetestracker.ModficationActivity.MedicationModification;
import com.ian.diabetestracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.ian.diabetestracker.Constants.Links.FETCH_MEDICATION_DETAILS;

public class MedicationActivity extends AppCompatActivity {

    RecyclerView mRecycler;
    TextView total;
    List<MedicationModel> mMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Medication");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mMed =new ArrayList<>();

        total  =findViewById(R.id.totalValue);

        mRecycler = findViewById(R.id.medicationRecyclerId);
        mRecycler.setHasFixedSize(true);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,FETCH_MEDICATION_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String m_value = jsonObject.getString("m_value");
                        String m_dosage = jsonObject.getString("m_dosage");
                        String m_uom = jsonObject.getString("m_uom");
//                        String m_dosage = jsonObject.getString("unit_of_measure");
                        String m_notes= jsonObject.getString("m_notes");
                        String m_date = jsonObject.getString("m_date");
                        String m_time = jsonObject.getString("m_time");

                        MedicationModel model = new MedicationModel(m_value,m_dosage,m_uom,m_date,m_time,m_notes);
                        mMed.add(model);
                    }

                    MedicationAdapter adapter = new MedicationAdapter(mMed,MedicationActivity.this);
                    mRecycler.setAdapter(adapter);
                    mRecycler.setLayoutManager(new LinearLayoutManager(MedicationActivity.this));

                    total.setText(String.valueOf(mMed.size()));

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
                startActivity(new Intent(MedicationActivity.this, MedicationModification.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MedicationActivity.this, MainActivity.class));
        finish();
    }
    @Override
    public boolean onSupportNavigateUp(){
        startActivity(new Intent(MedicationActivity.this, MainActivity.class));
        finish();
        return true;
    }
}
