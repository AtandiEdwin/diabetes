package com.ian.diabetestracker.MyActivities.DoctorApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ian.diabetestracker.R;

public class DoctorMainActivity extends AppCompatActivity {
    LinearLayout docMessage_layout,slot_layout,takenSlots_layout,analysis_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        slot_layout = findViewById(R.id.slot_layout);
        docMessage_layout = findViewById(R.id.docMessage_layout);
        takenSlots_layout=findViewById(R.id.takenSlots_layout);
        analysis_layout = findViewById(R.id.analysis_layout);


        docMessage_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorMainActivity.this, DoctorChatActivity.class));
            }
        });

        slot_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorMainActivity.this, SlotSet.class));
            }
        });
        takenSlots_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorMainActivity.this, TakenSlots.class));
            }
        });

        analysis_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorMainActivity.this, Analysis.class));
            }
        });

    }
}
