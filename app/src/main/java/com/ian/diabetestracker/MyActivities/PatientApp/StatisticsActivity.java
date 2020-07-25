package com.ian.diabetestracker.MyActivities.PatientApp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ian.diabetestracker.MainActivity;
import com.ian.diabetestracker.MyActivities.ChartHolderActivity;
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

    public void openBarchart(View view) {
        Intent intent = new Intent(StatisticsActivity.this, ChartHolderActivity.class);
        intent.putExtra("method","bars");
        startActivity(intent);
    }

    public void openPiechart(View view) {
        Intent intent = new Intent(StatisticsActivity.this,ChartHolderActivity.class);
        intent.putExtra("method","pie");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(StatisticsActivity.this, MainActivity.class));
        finish();
    }
}
