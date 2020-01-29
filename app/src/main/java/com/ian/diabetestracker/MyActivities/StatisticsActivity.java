package com.ian.diabetestracker.MyActivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ian.diabetestracker.R;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Statistics");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
