package com.fishmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import net.volleyrequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.fishAdapter;
import adapter.food_adapter;
import entry.growinfo;
import implement.getfeedinterface;

public class FishActivity extends AppCompatActivity implements getfeedinterface {
    private food_adapter myadapter;
    private food_adapter grow_apapter;
    private ArrayList<String>list,growlist;
    private ArrayList<growinfo>growinfos;
    private fishAdapter fishadapter;
    private ListView fishlist;
    private String info,uid;
    private  static int MODE=0;
    JSONArray array;
     JSONArray jsonArray;
    private Toolbar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);
        Intent intent=getIntent();
//        [{"fishName":"草鱼","fishId":1},{"fishName":"鲫鱼","fishId":2}]
        info=intent.getStringExtra("info");
        uid=intent.getStringExtra("uid");
        actionBar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FishActivity.this.finish();
            }
        });
        growlist = new ArrayList<String>();
        fishlist=(ListView) findViewById(R.id.fish_set);
        try {
            jsonArray=new JSONArray(info);
            int len=jsonArray.length();
            list=new ArrayList<>();
            for(int i=0;i<len;i++){
                list.add(jsonArray.getJSONObject(i).getString("short_name"));
            }
//            {"id":1,"short_name":"Carp","detail_name":"carp","min_temp":4,"nitrogen_waste_a":0.1985,"nitrogen_waste_b":2.9405,"p_waste_a":0.0045,"p_waste_b":0.0063,"memo":null}
//
            myadapter=new food_adapter(this,list);
            fishlist.setAdapter(myadapter);
            fishlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
//                {"levelId":1,"levelName":"Nursery","level_":"1",\
//                "startWeight":0,"endWeight":51,"TGC":0.0377702963429739,"exp":0.193252514500678,"fishId":1},\
//            {"levelId":2,"levelName":"Pre-Growout1","level_":"2",\
//                "startWeight":51,"endWeight":261,"TGC":0.000148533362329563,"exp":0.001,"fishId":1},\
//            {"levelId":3,"levelName":"Pre-Growout2","level_":"3",\
//                "startWeight":261,"endWeight":750,"TGC":1.38199875887825,"exp":0.499787224853563,"fishId":1},\
//            {"levelId":4,"levelName":"Growout","level_":"4",\
//                "startWeight":750,"endWeight":10000,"TGC":61.8829682902909,"exp":1,"fishId":1}
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (MODE == 0) {

                        JSONArray grow = null;
                        try {
                            int grownum = jsonArray.getJSONObject(position).getInt("min_temp");
                            int fish_id=jsonArray.getJSONObject(position).getInt("id");
                            String s="{\"fish_id\":\""+fish_id+"\"}";
                            volleyrequest request=new volleyrequest(FishActivity.this,uid,"getFishstages",s);
                            request.dogetfood();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        JSONArray grow = null;
                        try {
                            int grownum = array.length();
                                String growname=array.getJSONObject(position).getString("stg_name");
                            String startWeight=array.getJSONObject(position).getString("start_wgt");
                            String endWeight=array.getJSONObject(position).getString("end_wgt");
                            String TGC=array.getJSONObject(position).get("stg_tgc").toString();
                            String exp=array.getJSONObject(position).get("exp").toString();
                            growinfos=new ArrayList<growinfo>();
                            growinfos.add(new growinfo("阶段名称",growname));
                            growinfos.add(new growinfo("初始体重",startWeight));
                            growinfos.add(new growinfo("最终体重",endWeight));
                            growinfos.add(new growinfo("TGC",TGC));
                            growinfos.add(new growinfo("exp",exp));
                            fishadapter=new fishAdapter(FishActivity.this,growinfos);
                            fishlist.setAdapter(fishadapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    };
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        MODE=0;
        super.onResume();
    }

    @Override
    public void getfeed(String feed) {
        try {
            array=new JSONArray(feed);
            for(int i=0;i<array.length();i++){
                JSONObject json=array.getJSONObject(i);
                growlist.add(json.getString("stg_name"));
            }
            food_adapter newfishadapter=new food_adapter(this,growlist);
            fishlist.setAdapter(newfishadapter);
            MODE=1;
        } catch (JSONException e) {
            Toast.makeText(this,"没有数据", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
}
