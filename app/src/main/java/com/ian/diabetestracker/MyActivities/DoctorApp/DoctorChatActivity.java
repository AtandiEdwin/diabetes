package com.ian.diabetestracker.MyActivities.DoctorApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.ian.diabetestracker.Models.Messages;
import com.ian.diabetestracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ian.diabetestracker.Constants.Links.LOAD_MESSAGES;
import static com.ian.diabetestracker.Constants.Links.MESSAGES;

public class DoctorChatActivity extends AppCompatActivity {

    private static final String TAG = "MESSAGE";
    RecyclerView doctorRecycler;
    EditText edit_message;
    ImageButton doctor_send;
    List<Messages> myMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_chat);

        loadMessages();
        doctorRecycler = findViewById(R.id.doctorRecycler);
        edit_message  = findViewById(R.id.edit_message);
        doctor_send = findViewById(R.id.doctor_send);

        myMessage = new ArrayList<>();

        doctor_send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Message = edit_message.getText().toString();
                    String sender = "Doctor";
                    String Receiver = "Patient";
                    if(TextUtils.isEmpty(Message)){
                        Toast.makeText(DoctorChatActivity.this, "You cannot send an empty message", Toast.LENGTH_LONG).show();
                    }
                    else{
                        sendMessage(Message,sender,Receiver);
                    }

                }
            });
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

                            edit_message.setText("");
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
                    doctorRecycler.setHasFixedSize(true);
                    doctorRecycler.setLayoutManager(new LinearLayoutManager(DoctorChatActivity.this));
                    MessageAdapter adapter = new MessageAdapter(DoctorChatActivity.this,myMessage);
                    doctorRecycler.setAdapter(adapter);
                    doctorRecycler.scrollToPosition(adapter.getItemCount()-1);
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

}
