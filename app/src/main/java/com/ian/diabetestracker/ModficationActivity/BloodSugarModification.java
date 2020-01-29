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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.ian.diabetestracker.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.ian.diabetestracker.Constants.Links.SAVE_A1C_DETAILS;
import static com.ian.diabetestracker.Constants.Links.SAVE_SUGAR_DETAILS;

public class BloodSugarModification extends AppCompatActivity {
    EditText sugarconcentration,sugacondate,sugacontime,sugaconNotes;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar_modification);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Blood Sugar");
        actionBar.setDisplayHomeAsUpEnabled(true);

        sugacondate = findViewById(R.id.sugacondate);
        sugacontime = findViewById(R.id.sugacontime);
        sugaconNotes = findViewById(R.id.sugaconNotes);
        sugarconcentration =findViewById(R.id.sugarconcentration);

        spinner = findViewById(R.id.spinner);

        String[] units = {"Before breakfast","After Breakfast","Before lunch","After lunch","Before dinner","After dinner","Before sleep","After sleep"
        ,"Fasting","Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,units);
        spinner.setAdapter(adapter);

        Date D = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String date = fm.format(D);

        SimpleDateFormat tm= new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        String time = tm.format(D);

        sugacontime.setText(time);

        sugacondate.setText(date);


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
                if(TextUtils.isEmpty(sugarconcentration.getText().toString())  ){
                    sugarconcentration.setError("field can't be empty");
                }
                else{
                    final String concentration = sugarconcentration.getText().toString();
                    final String sdate = sugacondate.getText().toString();
                    final String stime = sugacontime.getText().toString();
                    final String snote = sugaconNotes.getText().toString();
                    final String when = spinner.getSelectedItem().toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, SAVE_SUGAR_DETAILS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            startActivity(new Intent(BloodSugarModification.this, BloodSugarActivity.class));
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
                            Map<String,String> smMap = new HashMap<>();
                            smMap.put("concentration",concentration);
                            smMap.put("smdate",sdate);
                            smMap.put("smtime",stime);
                            smMap.put("smnote",snote);
                            smMap.put("when",when);
                            return smMap;
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
        startActivity(new Intent(BloodSugarModification.this, BloodSugarActivity.class));
        finish();
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
