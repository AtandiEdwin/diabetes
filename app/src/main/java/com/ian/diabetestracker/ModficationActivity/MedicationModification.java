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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.ian.diabetestracker.MyActivities.BloodSugarActivity;
import com.ian.diabetestracker.MyActivities.MedicationActivity;
import com.ian.diabetestracker.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.ian.diabetestracker.Constants.Links.SAVE_A1C_DETAILS;
import static com.ian.diabetestracker.Constants.Links.SAVE_MEDICATION_DETAILS;

public class MedicationModification extends AppCompatActivity {

    EditText medicationName,mmNotes,mmtime,mmdate,mmdosage;
    Spinner spinner;
    TextView doseMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_modification);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Medication");
        actionBar.setDisplayHomeAsUpEnabled(true);

        medicationName  = findViewById(R.id.medicationName);
        mmdosage = findViewById(R.id.mmdosage);
        mmdate = findViewById(R.id.mmdate);
        mmtime = findViewById(R.id.mmtime);
        mmNotes = findViewById(R.id.mmNotes);

        doseMeasure = findViewById(R.id.doseMeasure);

        spinner = findViewById(R.id.spinner);
        String[] units = {"mg","unit","mL","tablet"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,units);
        spinner.setAdapter(adapter);

        Date D = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String date = fm.format(D);

        SimpleDateFormat tm= new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        String time = tm.format(D);


        mmdate.setText(date);
        mmtime.setText(time);

        SpinnerListener(spinner);



    }

    private void SpinnerListener(Spinner spinner)
    {
        spinner.setOnItemSelectedListener(new mySelectedListener());
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

                if(TextUtils.isEmpty(medicationName.getText().toString())|| TextUtils.isEmpty(mmdosage.getText().toString()) ){
                    medicationName.setError("field can't be empty");
                    mmdosage.setError("field can't be empty");
                }
                else{
                    final String medication = medicationName.getText().toString();
                    final String mmdosages = mmdosage.getText().toString();
                    final String dosemeasures = doseMeasure.getText().toString();
                    final String mmtimes = mmtime.getText().toString();
                    final String mmdates= mmdate.getText().toString();
                    final  String mnotes = mmNotes.getText().toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, SAVE_MEDICATION_DETAILS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            startActivity(new Intent(MedicationModification.this, MedicationActivity.class));
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
                            Map<String,String> mMap = new HashMap<>();
                            mMap.put("medication",medication);
                            mMap.put("mdate",mmdates);
                            mMap.put("mtime",mmtimes);
                            mMap.put("mnote",mnotes);
                            mMap.put("munit",dosemeasures);
                            mMap.put("dose",mmdosages);
                            return mMap;
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

    public class mySelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView parent, View view, int pos, long id) {
            String s1 = String.valueOf(parent.getItemAtPosition(pos));
            doseMeasure.setText(s1);
        }

        @Override
        public void onNothingSelected(AdapterView parent) {
        }

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MedicationModification.this, MedicationActivity.class));
        finish();
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
