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

public class A1cModification extends AppCompatActivity {

    EditText dosage,acondate,acontime,aconNotes;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1c_modification);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("A1c");
        actionBar.setDisplayHomeAsUpEnabled(true);

        dosage = findViewById(R.id.adosage);
        acondate =findViewById(R.id.acondate);
        acontime = findViewById(R.id.acontime);
        aconNotes=findViewById(R.id.aNotes);

        spinner = findViewById(R.id.spinner);
        String[] units = {"A1C","eAG"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,units);
        spinner.setAdapter(adapter);

        Date D = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String date = fm.format(D);

        SimpleDateFormat tm= new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        String time = tm.format(D);

        acondate.setText(date);
        acontime.setText(time);


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

                if(TextUtils.isEmpty(dosage.getText().toString()) || TextUtils.isEmpty(acondate.getText().toString())
                        || TextUtils.isEmpty(acontime.getText().toString()) ){
                    dosage.setError("field can't be empty");
                    aconNotes.setError("field can't be empty");

                }
                else{
                    final String adosage = dosage.getText().toString();
                    final String adate = acondate.getText().toString();
                    final String atime = acontime.getText().toString();
                    final String anote = aconNotes.getText().toString();
                    final String aunit = spinner.getSelectedItem().toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, SAVE_A1C_DETAILS, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            startActivity(new Intent(A1cModification.this, A1cActivity.class));
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
                            Map<String,String> aMap = new HashMap<>();
                            aMap.put("adosage",adosage);
                            aMap.put("adate",adate);
                            aMap.put("atime",atime);
                            aMap.put("anote",anote);
                            aMap.put("aunit",aunit);
                            return aMap;
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
        startActivity(new Intent(A1cModification.this, A1cActivity.class));
        finish();
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
