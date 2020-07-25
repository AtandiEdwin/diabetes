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
import com.ian.diabetestracker.Adapters.A1cAdapter;
import com.ian.diabetestracker.MainActivity;
import com.ian.diabetestracker.Models.A1cModel;
import com.ian.diabetestracker.ModficationActivity.A1cModification;
import com.ian.diabetestracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.ian.diabetestracker.Constants.Links.FETCH_A1C_DETAILS;

public class A1cActivity extends AppCompatActivity {

    RecyclerView aRecycler;
    List<A1cModel> mA1c;
    TextView totalvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1c);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("A1c");
        actionBar.setDisplayHomeAsUpEnabled(true);

        mA1c = new ArrayList<>();

        totalvalue = findViewById(R.id.totalValue);

        aRecycler = findViewById(R.id.A1cRecyclerId);


        StringRequest stringRequest = new StringRequest(Request.Method.GET,FETCH_A1C_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String A1c_concentration = jsonObject.getString("A1c_concentration");
                        String A1c_uom = jsonObject.getString("A1c_uom");
                        String A1c_date = jsonObject.getString("A1c_date");
                        String A1c_time = jsonObject.getString("A1c_time");
                        String A1c_notes = jsonObject.getString("A1c_notes");

                        A1cModel model = new A1cModel(A1c_concentration,A1c_uom,A1c_date,A1c_time,A1c_notes);
                        mA1c.add(model);
                    }

                    A1cAdapter adapter = new A1cAdapter(A1cActivity.this,mA1c);
                    aRecycler.setAdapter(adapter);
                    aRecycler.setLayoutManager(new LinearLayoutManager(A1cActivity.this));

                    totalvalue.setText(String.valueOf(mA1c.size()));

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
                startActivity(new Intent(A1cActivity.this, A1cModification.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(A1cActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
