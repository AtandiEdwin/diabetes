package com.ian.diabetestracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ian.diabetestracker.MyActivities.PatientApp.A1cActivity;
import com.ian.diabetestracker.MyActivities.PatientApp.BloodPressureActivity;
import com.ian.diabetestracker.MyActivities.PatientApp.BloodSugarActivity;
import com.ian.diabetestracker.MyActivities.DoctorApp.DoctorMainActivity;
import com.ian.diabetestracker.MyActivities.PatientApp.HelpActivity;
import com.ian.diabetestracker.MyActivities.PatientApp.MedicationActivity;
import com.ian.diabetestracker.MyActivities.PatientApp.PatientChatActivity;
import com.ian.diabetestracker.MyActivities.PatientApp.SlotsActivity;
import com.ian.diabetestracker.MyActivities.PatientApp.StatisticsActivity;
import com.ian.diabetestracker.MyActivities.PatientApp.WeightActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout statistics,bloodSugar,medication,bloodPressure,weigth,a1c,Message_layout,docMessage_layout,slot,slot_layout,help;
    ImageView go_doctor;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go_doctor = findViewById(R.id.go_doctor);
        statistics = findViewById(R.id.statistics);
        bloodSugar = findViewById(R.id.bloodSugar);
        medication = findViewById(R.id.medication);
        slot_layout = findViewById(R.id.slot_layout);
        bloodPressure = findViewById(R.id.bloodPressure);
        weigth  =findViewById(R.id.weight);
        a1c = findViewById(R.id.a1c);
        Message_layout = findViewById(R.id.Message_layout);
        docMessage_layout = findViewById(R.id.docMessage_layout);
        help = findViewById(R.id.help);

        slot = findViewById(R.id.slotman);


        slot.setOnClickListener(this);
        statistics.setOnClickListener(this);
        Message_layout.setOnClickListener(this);
        bloodSugar.setOnClickListener(this);
        medication.setOnClickListener(this);
        bloodPressure.setOnClickListener(this);
        weigth.setOnClickListener(this);
        a1c.setOnClickListener(this);
        go_doctor.setOnClickListener(this);
        help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.statistics){
            startActivity(new Intent(MainActivity.this, StatisticsActivity.class));
            finish();
        }
        else if(v.getId()==R.id.bloodSugar){
            startActivity(new Intent(MainActivity.this, BloodSugarActivity.class));
            finish();
        }
        else if(v.getId()==R.id.medication){
            startActivity(new Intent(MainActivity.this, MedicationActivity.class));
            finish();
        }
        else if(v.getId()==R.id.bloodPressure){
            startActivity(new Intent(MainActivity.this, BloodPressureActivity.class));
            finish();
        }
        else if(v.getId()==R.id.weight){
            startActivity(new Intent(MainActivity.this, WeightActivity.class));
            finish();
        }
        else if(v.getId()==R.id.slotman){
            startActivity(new Intent(MainActivity.this, SlotsActivity.class));
            finish();
        }
        else if(v.getId()==R.id.a1c){
            startActivity(new Intent(MainActivity.this, A1cActivity.class));
            finish();
        }
        else if(v.getId()==R.id.Message_layout){
            startActivity(new Intent(MainActivity.this, PatientChatActivity.class));
            finish();
        }
        else if(v.getId()==R.id.go_doctor){
            startActivity(new Intent(MainActivity.this, DoctorMainActivity.class));
            finish();
        }
        else if(v.getId()==R.id.help){
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
