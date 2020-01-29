package com.ian.diabetestracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ian.diabetestracker.MyActivities.A1cActivity;
import com.ian.diabetestracker.MyActivities.BloodPressureActivity;
import com.ian.diabetestracker.MyActivities.BloodSugarActivity;
import com.ian.diabetestracker.MyActivities.MedicationActivity;
import com.ian.diabetestracker.MyActivities.StatisticsActivity;
import com.ian.diabetestracker.MyActivities.WeightActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout statistics,bloodSugar,medication,bloodPressure,weigth,a1c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statistics = findViewById(R.id.statistics);
        bloodSugar = findViewById(R.id.bloodSugar);
        medication = findViewById(R.id.medication);
        bloodPressure = findViewById(R.id.bloodPressure);
        weigth  =findViewById(R.id.weight);
        a1c = findViewById(R.id.a1c);


        statistics.setOnClickListener(this);
        bloodSugar.setOnClickListener(this);
        medication.setOnClickListener(this);
        bloodPressure.setOnClickListener(this);
        weigth.setOnClickListener(this);
        a1c.setOnClickListener(this);

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
        else if(v.getId()==R.id.a1c){
            startActivity(new Intent(MainActivity.this, A1cActivity.class));
            finish();
        }
    }
}
