package com.fishmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ui.LineChartManager;

public class ChartActivity extends AppCompatActivity {
    private LineChart lineChart;
    private Toolbar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        lineChart=(LineChart)findViewById(R.id.linechart);
        Intent intent=getIntent();
        String info=intent.getStringExtra("info");
        actionBar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChartActivity.this.finish();
            }
        });
        if(info.contains("weight_list")) {
            try {
                ArrayList<String> xlist = new ArrayList<>();
                ArrayList<Entry> ylist = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(info);
                String s = jsonObject.getString("weight_list");
                String[] args = s.split(",");
                for (int i = 0; i < args.length; i++) {
                    xlist.add(Integer.toString(i + 1));
                    ylist.add(new Entry(Float.parseFloat(args[i]), i + 1));

                }
                LineChartManager.setLineName("生长状况");
                LineChartManager.initSingleLineChart(this, lineChart, xlist, ylist);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//{"start_day":0,"end_day":9,"day_type":2,"solid_n_waste":"0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000",
// "dissolved_n_waste":"-0.002,-0.002,-0.002,-0.002,-0.002,-0.002,-0.002,-0.002,-0.002,-0.002"}
        else if(info.contains("solid_n_waste")){
            try {
                ArrayList<String> xlist = new ArrayList<>();
                ArrayList<Entry> ylist = new ArrayList<>();
                ArrayList<Entry>ylist1=new ArrayList<>();
                JSONObject jsonObject = new JSONObject(info);
                String s = jsonObject.getString("solid_n_waste");
                String[] args = s.split(",");
                for (int i = 0; i < args.length; i++) {
                    xlist.add(Integer.toString(i + 1));
                    ylist.add(new Entry(Float.parseFloat(args[i]), i + 1));

                }
                String s1 = jsonObject.getString("dissolved_n_waste");
                String[] args1 = s1.split(",");
                for (int i = 0; i < args1.length; i++) {
                    ylist1.add(new Entry(Float.parseFloat(args1[i]), i + 1));

                }

                LineChartManager.setLineName("氮吸收");
                LineChartManager.setLineName1("氮排放");
                LineChartManager.initDoubleLineChart(this, lineChart, xlist,ylist,ylist1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(info.contains("solid_p_waste")){
            try {
                ArrayList<String> xlist = new ArrayList<>();
                ArrayList<Entry> ylist = new ArrayList<>();
                ArrayList<Entry>ylist1=new ArrayList<>();
                JSONObject jsonObject = new JSONObject(info);
                String s = jsonObject.getString("solid_p_waste");
                String[] args = s.split(",");
                for (int i = 0; i < args.length; i++) {
                    xlist.add(Integer.toString(i + 1));
                    ylist.add(new Entry(Float.parseFloat(args[i]), i + 1));

                }
                String s1 = jsonObject.getString("dissolved_p_waste");
                String[] args1 = s1.split(",");
                for (int i = 0; i < args1.length; i++) {
                    ylist1.add(new Entry(Float.parseFloat(args1[i]), i + 1));

                }

                LineChartManager.setLineName("磷吸收");
                LineChartManager.setLineName1("磷排放");
                LineChartManager.initDoubleLineChart(this, lineChart, xlist,ylist,ylist1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
