package com.ian.diabetestracker.ModficationActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.ian.diabetestracker.MyActivities.A1cActivity;
import com.ian.diabetestracker.MyActivities.BloodPressureActivity;
import com.ian.diabetestracker.MyActivities.BloodSugarActivity;
import com.ian.diabetestracker.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.ian.diabetestracker.Constants.Links.SAVE_A1C_DETAILS;
import static com.ian.diabetestracker.Constants.Links.SAVE_PRESSURE_DETAILS;

public class BloodPressureMoodification extends AppCompatActivity {

    EditText pmsystolicPressure,pmdiastolicPressure,pmdate,pmtime,pmNotes,pulseValue;
    Spinner spinner;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_moodification);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Blood Pressure");
        actionBar.setDisplayHomeAsUpEnabled(true);

        pmsystolicPressure = findViewById(R.id.pmsystolicPressure);
        pmdiastolicPressure = findViewById(R.id.pmdiastolicPressure);
        pmdate = findViewById(R.id.pmdate);
        pmtime = findViewById(R.id.pmtime);
        pmNotes = findViewById(R.id.pmNotes);
        pulseValue = findViewById(R.id.pulseValue);

        spinner = findViewById(R.id.spinner);

        checkBox = findViewById(R.id.pulseCheck);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    pulseValue.setVisibility(View.VISIBLE);
                }
                else if(!checkBox.isChecked()){
                    pulseValue.setVisibility(View.GONE);
                }
            }
        });

        spinner = findViewById(R.id.spinner);
        String[] units = {"Right","Left"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,units);
        spinner.setAdapter(adapter);

        Date D = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String date = fm.format(D);

        SimpleDateFormat tm= new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        String time = tm.format(D);

        pmtime.setText(time);

        pmdate.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mod_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnok:

                if(TextUtils.isEmpty(pmsystolicPressure.getText().toString()) || TextUtils.isEmpty(pmdiastolicPressure.getText().toString())){
                    pmsystolicPressure.setError("field can't be empty");
                    pmdiastolicPressure.setError("field can't be empty");

                }
                else{
                    final String pulses = pulseValue.getText().toString();
                    final String pmsysto = pmsystolicPressure.getText().toString();
                    final String pmdiast = pmdiastolicPressure.getText().toString();
                    final String pmdates = pmdate.getText().toString();
                    final String pmtimes = pmtime.getText().toString();
                    final String pmnote = pmNotes.getText().toString();
                    final String arm = spinner.getSelectedItem().toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, SAVE_PRESSURE_DETAILS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            startActivity(new Intent(BloodPressureMoodification.this, BloodPressureActivity.class));
                            finish();

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> pmMap = new HashMap<>();
                            pmMap.put("systo",pmsysto);
                            pmMap.put("diasto",pmdiast);
                            pmMap.put("arm",arm);
                            pmMap.put("pnote",pmnote);
                            pmMap.put("pdate",pmdates);
                            pmMap.put("ptime",pmtimes);
                            pmMap.put("pulse",pulses);
                            return pmMap;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BloodPressureMoodification.this, BloodPressureActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
