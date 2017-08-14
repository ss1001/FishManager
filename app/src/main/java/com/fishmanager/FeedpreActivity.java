package com.fishmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ui.LineChartManager;

public class FeedpreActivity extends AppCompatActivity {
    private LineChart lineChart;
    private static int Mode=0;
    private Button feed,feeddig,num,ration;
    JSONObject jsonObject;
    String info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedpre);
        lineChart = (LineChart) findViewById(R.id.linechart);
        feed=(Button)findViewById(R.id.btn_feed);
        num=(Button)findViewById(R.id.btn_num);
        ration=(Button)findViewById(R.id.btn_rate);
        feeddig=(Button)findViewById(R.id.btn_deg);

        Intent intent = getIntent();
         info= intent.getStringExtra("info");
        if (info.contains("feed_eff")) {
            try {
                ArrayList<String> xlist = new ArrayList<>();
                ArrayList<Entry> ylist = new ArrayList<>();
               jsonObject  = new JSONObject(info);
                if(Mode==0) {
                    String s = jsonObject.getString("feed_req");
                    String[] args = s.split(",");
                    for (int i = 0; i < args.length; i++) {
                        xlist.add(Integer.toString(i + 1));
                        ylist.add(new Entry(Float.parseFloat(args[i]), i + 1));

                    }
                    LineChartManager.setLineName("饲料消费");
                    LineChartManager.initSingleLineChart(this, lineChart, xlist, ylist);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList<String> xlist = new ArrayList<>();
                    ArrayList<Entry> ylist = new ArrayList<>();
                    jsonObject  = new JSONObject(info);
                    if(Mode==0) {
                        String s = jsonObject.getString("feed_req");
                        String[] args = s.split(",");
                        for (int i = 0; i < args.length; i++) {
                            xlist.add(Integer.toString(i + 1));
                            ylist.add(new Entry(Float.parseFloat(args[i]), i + 1));

                        }
                        LineChartManager.setLineName("饲料消费");
                        LineChartManager.initSingleLineChart(FeedpreActivity.this, lineChart, xlist, ylist);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // //{"start_day":0,"end_day":9,"day_type":2,"feed_req":"0.206,0.226,0.247,0.270,0.292,0.317,0.343,0.368,0.397,0.425",
        // "feed_rate":"2.943,2.824,2.722,2.642,2.543,2.465,2.396,2.320,2.259,2.192",
        // "feed_eff":"0.684,0.678,0.673,0.670,0.664,0.660,0.656,0.651,0.648,0.643",
        // "feed_digEnergy":"17.758,17.917,18.051,18.130,18.301,18.414,18.514,18.659,18.758,18.898"}
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList<String> xlist = new ArrayList<>();
                    ArrayList<Entry> ylist = new ArrayList<>();
                    jsonObject  = new JSONObject(info);
                    if(Mode==0) {
                        String s = jsonObject.getString("feed_rate");
                        String[] args = s.split(",");
                        for (int i = 0; i < args.length; i++) {
                            xlist.add(Integer.toString(i + 1));
                            ylist.add(new Entry(Float.parseFloat(args[i]), i + 1));

                        }
                        LineChartManager.setLineName("饲料系数");
                        LineChartManager.initSingleLineChart(FeedpreActivity.this, lineChart, xlist, ylist);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList<String> xlist = new ArrayList<>();
                    ArrayList<Entry> ylist = new ArrayList<>();
                    jsonObject  = new JSONObject(info);
                    if(Mode==0) {
                        String s = jsonObject.getString("feed_eff");
                        String[] args = s.split(",");
                        for (int i = 0; i < args.length; i++) {
                            xlist.add(Integer.toString(i + 1));
                            ylist.add(new Entry(Float.parseFloat(args[i]), i + 1));

                        }
                        LineChartManager.setLineName("摄食率");
                        LineChartManager.initSingleLineChart(FeedpreActivity.this, lineChart, xlist, ylist);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        feeddig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ArrayList<String> xlist = new ArrayList<>();
                    ArrayList<Entry> ylist = new ArrayList<>();
                    jsonObject  = new JSONObject(info);
                    if(Mode==0) {
                        String s = jsonObject.getString("feed_digEnergy");
                        String[] args = s.split(",");
                        for (int i = 0; i < args.length; i++) {
                            xlist.add(Integer.toString(i + 1));
                            ylist.add(new Entry(Float.parseFloat(args[i]), i + 1));

                        }
                        LineChartManager.setLineName("消化率");
                        LineChartManager.initSingleLineChart(FeedpreActivity.this, lineChart, xlist, ylist);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}