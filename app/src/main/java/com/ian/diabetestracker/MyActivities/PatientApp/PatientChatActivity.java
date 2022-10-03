package com.ian.diabetestracker.MyActivities.PatientApp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ian.diabetestracker.Adapters.MessageAdapter;
import com.ian.diabetestracker.MainActivity;
import com.ian.diabetestracker.Models.Messages;
import com.ian.diabetestracker.R;
import com.ian.diabetestracker.TouchHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ian.diabetestracker.Constants.Links.LOAD_MESSAGES;
import static com.ian.diabetestracker.Constants.Links.MESSAGES;

public class PatientChatActivity extends AppCompatActivity  {
    private static final String TAG = "MESSAGE";
    RecyclerView patientRecycler;
    EditText patient_message;
    ImageButton patient_send;
    SwipeRefreshLayout refresh_Layout;

    List<Messages> myMessage;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_chat);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Patient Chat Room");
        actionBar.setDisplayHomeAsUpEnabled(true);
        loadMessages();
        patientRecycler = findViewById(R.id.patientRecycler);
        patient_message  = findViewById(R.id.patient_message);
        patient_send = findViewById(R.id.patient_send);
        refresh_Layout = findViewById(R.id.refresh_Layout);

        refresh_Layout.setColorSchemeColors(android.R.color.holo_orange_dark, android.R.color.holo_blue_bright, android.R.color.holo_blue_dark, android.R.color
        .holo_green_dark);
        refresh_Layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(PatientChatActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
            }
        });

        myMessage = new ArrayList<>();

        patient_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Message = patient_message.getText().toString();
                String sender = "Patient";
                String Receiver = "Doctor";
                if(TextUtils.isEmpty(Message)){
                    Toast.makeText(PatientChatActivity.this, "You cannot send an empty message", Toast.LENGTH_LONG).show();
                }
                else{
                    sendMessage(Message,sender,Receiver);
                }

            }
        });


    }

    private void loadMessages() {
        StringRequest mstringRequest = new StringRequest(Request.Method.POST, LOAD_MESSAGES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String message = jsonObject.getString("message");
                        String sender = jsonObject.getString("sender");
                        String receiver = jsonObject.getString("receiver");

                        Messages messages = new Messages(message,sender,receiver);
                        myMessage.add(messages);
                    }
                    patientRecycler.setHasFixedSize(true);
                    patientRecycler.setLayoutManager(new LinearLayoutManager(PatientChatActivity.this));
                    MessageAdapter adapter = new MessageAdapter(PatientChatActivity.this,myMessage);
                    patientRecycler.setAdapter(adapter);
                    patientRecycler.scrollToPosition(adapter.getItemCount()-1);

                    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new TouchHelper(adapter));
                    itemTouchHelper.attachToRecyclerView(patientRecycler);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: ERROR" + error);

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(mstringRequest);


    }

    private void sendMessage(final String message, final String sender, final String receiver) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, MESSAGES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String message = jsonObject.getString("message");
                        String sender = jsonObject.getString("sender");
                        String receiver = jsonObject.getString("receiver");

                        Messages messages = new Messages(message,sender,receiver);
                        myMessage.add(messages);

                        patient_message.setText("");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: ERROR" + error);

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("message",message);
                map.put("sender",sender);
                map.put("receiver",receiver);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(PatientChatActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(PatientChatActivity.this, MainActivity.class));
        finish();
        return true;
    }
}
