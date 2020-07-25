package com.ian.diabetestracker;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.github.mikephil.charting.charts.BarChart;
//import com.github.mikephil.charting.charts.PieChart;
//import com.github.mikephil.charting.components.Description;
//import com.github.mikephil.charting.data.BarData;
//import com.github.mikephil.charting.data.BarDataSet;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.data.PieData;
//import com.github.mikephil.charting.data.PieDataSet;
//import com.github.mikephil.charting.data.PieEntry;
//import com.github.mikephil.charting.utils.ColorTemplate;
import com.ian.diabetestracker.Constants.ApiClient;
import com.ian.diabetestracker.Constants.ApiInterface;
import com.ian.diabetestracker.Models.Pressure;

//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;

public class ChartFragment extends Fragment {

//    private BarChart mbarchart;
//    private PieChart mpiechart;

    public ChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_chart, container, false);
//        mbarchart = v.findViewById(R.id.barchat);
//        mpiechart = v.findViewById(R.id.peichart);
        getPressureInfo(getArguments().getString("method"));
        return v;
    }

    private void getPressureInfo(final String method){
//        Call<List<Pressure>> call = ApiClient.getApiClient().create(ApiInterface.class).getPressureInfo();
//
//        call.enqueue(new Callback<List<Pressure>>() {
//            @Override
//            public void onResponse(Call<List<Pressure>> call, Response<List<Pressure>> response) {
//                if(response.body()!=null){
//                    if(method.equals("bars")){
//                        List<BarEntry> barEntries = new ArrayList<>();
//
//                        for(Pressure pressure: response.body()){
//                            barEntries.add(new BarEntry(pressure.getPulse(),pressure.getDay()));
//
//                            BarDataSet barDataSet = new BarDataSet(barEntries,"Pressure");
//                            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//
//                            BarData barData = new BarData(barDataSet);
//                            barData.setBarWidth(0.9f);
//
//                            mbarchart.setVisibility(View.VISIBLE);
//                            mbarchart.animateY(5000);
//                            mbarchart.setData(barData);
//                            mbarchart.setFitBars(true);
//
//                            Description description = new Description();
//                            description.setText("PressureMeassure Per DAy");
//                            mbarchart.setDescription(description);
//                            mbarchart.invalidate();
//                        }
//
//                    }
//
//                    else if(method.equals("pie")){
//
//                        List<PieEntry> pieEntries = new ArrayList<>();
//
//                        for(Pressure pressure : response.body()){
//                            pieEntries.add(new PieEntry(pressure.getPulse(),Integer.toString(pressure.getDay())));
//                        }
//
//                        mpiechart.setVisibility(View.VISIBLE);
//                        mpiechart.animateXY(5000,5000);
//
//                        PieDataSet pieDataSet = new PieDataSet(pieEntries,"Pressure by day");
//                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//
//                        PieData pieData = new PieData(pieDataSet);
//
//                        mpiechart.setData(pieData);
//
//                        Description description = new Description();
//                        description.setText("Pressure rates per day");
//                        mpiechart.setDescription(description);
//                        mpiechart.invalidate();
//
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Pressure>> call, Throwable t) {
//
//            }
//        });
//


    }


}
