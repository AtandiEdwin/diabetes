package com.ian.diabetestracker.MyActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ian.diabetestracker.ChartFragment;
import com.ian.diabetestracker.R;

public class ChartHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_holder);

        String method = getIntent().getStringExtra("method");
        ChartFragment chartFragment = new ChartFragment();
        Bundle bundle = new Bundle();
        bundle.putString("method",method);
        chartFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,chartFragment).commit();
    }
}
