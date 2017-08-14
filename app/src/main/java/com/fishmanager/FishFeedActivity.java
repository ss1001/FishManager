package com.fishmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import net.getfood;
import net.volleyrequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import adapter.fishfeedAdapter;
import implement.getfeedinterface;

public class FishFeedActivity extends AppCompatActivity implements getfeedinterface {
        private String uid,info;
    private ListView lv_fishfeed;
    private ArrayList<String>stages;
    private ArrayList<String>feeds;
    private Button submit;
    private Toolbar actionBar;
    JSONArray feedsarray;
    JSONArray array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_feed);
        actionBar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FishFeedActivity.this.finish();
            }
        });
        Intent intent=getIntent();
        info=intent.getStringExtra("info");
        uid=intent.getStringExtra("uid");
        init();
    }
    private void init(){
        lv_fishfeed=(ListView)findViewById(R.id.lv_feedfish);
        submit=(Button)findViewById(R.id.submit);
        getfood get_food=new getfood(this,uid);
        get_food.dogetfood();
        stages=new ArrayList<>();
        feeds=new ArrayList<>();

    }

    @Override
    public void getfeed(String feed) {
        try {
            if(feed.contains("message")){
                Toast.makeText(FishFeedActivity.this,"success",Toast.LENGTH_SHORT).show();
            }
            array=new JSONArray(info);
            for(int i=0;i<array.length();i++){
                String stg_name=array.getJSONObject(i).getString("stg_name");
                stages.add(stg_name);
            }
            feedsarray=new JSONArray(feed);
            for(int i=0;i<feedsarray.length();i++){
                String feed_name=feedsarray.getJSONObject(i).getString("feed_name");
                feeds.add(feed_name);
            }
            fishfeedAdapter Myadapter=new fishfeedAdapter(this,stages,feeds);
            lv_fishfeed.setAdapter(Myadapter);
            lv_fishfeed.invalidate();
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0;i<array.length();i++){
                        try {
                            String fish_id=array.getJSONObject(i).getString("id");
                            String feed_id=feedsarray.getJSONObject(i).getString("id");
                            String param="{\"stg_id\":"+fish_id+",\"feed_id\":"+feed_id+"}";
                            volleyrequest volleyrequest=new volleyrequest(FishFeedActivity.this,uid,"updatefeedforstage",param);
                            volleyrequest.dogetfood();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
