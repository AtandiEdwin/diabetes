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
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ian.diabetestracker.MyActivities.PatientApp.WeightActivity;
import com.ian.diabetestracker.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.ian.diabetestracker.Constants.Links.SAVE_WEIGHT_DETAILS;

public class WeightModification extends AppCompatActivity {

    EditText wmMeasure,wmdate,wmtime,wmNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_modification);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Weight");
        actionBar.setDisplayHomeAsUpEnabled(true);

        wmdate = findViewById(R.id.wmdate);
        wmMeasure = findViewById(R.id.wmMeasure);
        wmtime = findViewById(R.id.wmtime);
        wmNotes = findViewById(R.id.wmNotes);

        Date D = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String date = fm.format(D);

        SimpleDateFormat tm= new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        String time = tm.format(D);

        wmtime.setText(time);

        wmdate.setText(date);

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
                if(TextUtils.isEmpty(wmMeasure.getText().toString())){
                    wmMeasure.setError("Weight can't be empty");
                }
                else{
                    final String measure = wmMeasure.getText().toString();
                    final String wdate = wmdate.getText().toString();
                    final String wtime = wmtime.getText().toString();
                    final String wnote = wmNotes.getText().toString();


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, SAVE_WEIGHT_DETAILS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            startActivity(new Intent(WeightModification.this, WeightActivity.class));
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
                            Map<String,String> wMap = new HashMap<>();
                            wMap.put("measure",measure);
                            wMap.put("wmdate",wdate);
                            wMap.put("wmtime",wtime);
                            wMap.put("wmnote",wnote);
                            return wMap;
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
        startActivity(new Intent(WeightModification.this, WeightActivity.class));
        finish();
    }
//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
}
