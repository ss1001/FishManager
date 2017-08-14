package com.fishmanager;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import net.grow_predict;
import net.volleyrequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.food_adapter;
import implement.getfeedinterface;

public class PredictActivity extends AppCompatActivity implements getfeedinterface {
        private Toolbar actionBar;
    private Button grow_button,feed_btn,feed_pre,btn_P,btn_N,clear;
    private EditText begin,temp,lowtemp,weeks;
    private Spinner fish;
    String uid,info;
    JSONArray array;
    ArrayList<String>fishes;
    static int mode=-1;

    @Override
    protected void onResume() {
        mode=-1;
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);
        Intent intent=getIntent();
        uid=intent.getStringExtra("uid");
        info=intent.getStringExtra("info");
        actionBar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PredictActivity.this.finish();
            }
        });
        init();
        try {
            array=new JSONArray(info);
            for(int i=0;i<array.length();i++){
                String name=array.getJSONObject(i).getString("short_name");
                fishes.add(name);
            }
            food_adapter fishadapter=new food_adapter(this,fishes);
            fish.setAdapter(fishadapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void init(){
        clear=(Button)findViewById(R.id.clear);

        btn_P=(Button)findViewById(R.id.btn_P);
        btn_N=(Button)findViewById(R.id.btn_N);
        grow_button=(Button)findViewById(R.id.button_grow);
        begin=(EditText)findViewById(R.id.begin);
        weeks=(EditText)findViewById(R.id.weeks);
        temp=(EditText)findViewById(R.id.temperature);
        lowtemp=(EditText)findViewById(R.id.lowtemp);
        begin.setText("10");
        weeks.setText("10");
        lowtemp.setText("4");
        temp.setText("20");
        fish=(Spinner)findViewById(R.id.spinner);
        fishes=new ArrayList<>();
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                begin.setText("");
                weeks.setText("");
                lowtemp.setText("");
                temp.setText("");
            }
        });
        grow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=fish.getSelectedItemPosition();
                mode=0;
         //       {"fish_id":10.5628282}
                try {
                    String fparam="{\"fish_id\":"+array.getJSONObject(position).getString("id")+",\"init_wgt\":"+begin.getText()+
                           ",\"minimum_temp\":"+lowtemp.getText()+ ",\"avg_temp\":"+temp.getText()+",\"end_day\":"+weeks.getText()+",\"time_unit\":2"+"}";
                    volleyrequest request=new volleyrequest(PredictActivity.this,uid,"getweightprediction",fparam);
                    request.dogetfood();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        feed_btn=(Button)findViewById(R.id.button2);
        feed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=1;
                int position=fish.getSelectedItemPosition();
                int fish_id= 0;
                try {
                    fish_id = array.getJSONObject(position).getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String s="{\"fish_id\":\""+fish_id+"\"}";
                volleyrequest request=new volleyrequest(PredictActivity.this,uid,"getFishstages",s);
                request.dogetfood();
            }
        });
        feed_pre=(Button)findViewById(R.id.btn_feedpre);
        feed_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=3;
                int position=fish.getSelectedItemPosition();
                String fparam= null;
                try {
                    fparam = "{\"fish_id\":"+array.getJSONObject(position).getString("id")+",\"init_wgt\":"+begin.getText()+
                            ",\"minimum_temp\":"+lowtemp.getText()+ ",\"avg_temp\":"+temp.getText()+",\"end_day\":"+weeks.getText()+",\"time_unit\":2"+"}";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyrequest request=new volleyrequest(PredictActivity.this,uid,"getfeedrequirement",fparam);
                request.dogetfood();
            }
        });

        btn_N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=0;
                int position=fish.getSelectedItemPosition();
                String fparam= null;
                try {
                    JSONObject object=array.getJSONObject(position);
                    fparam = "{\"fish_id\":"+array.getJSONObject(position).getString("id")+",\"init_wgt\":"+begin.getText()+
                            ",\"minimum_temp\":"+lowtemp.getText()+ ",\"avg_temp\":"+temp.getText()+",\"end_day\":"+weeks.getText()+",\"time_unit\":2"+"}";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyrequest request=new volleyrequest(PredictActivity.this,uid,"getnwaste",fparam);
                request.dogetfood();
            }
        });
        btn_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mode=0;
                int position=fish.getSelectedItemPosition();
                String fparam= null;
                try {
                    fparam = "{\"fish_id\":"+array.getJSONObject(position).getString("id")+",\"init_wgt\":"+begin.getText()+
                            ",\"minimum_temp\":"+lowtemp.getText()+ ",\"avg_temp\":"+temp.getText()+",\"end_day\":"+weeks.getText()+",\"time_unit\":2"+"}";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyrequest request=new volleyrequest(PredictActivity.this,uid,"getpwaste",fparam);
                request.dogetfood();
            }
        });
        try {
            JSONArray array1=new JSONArray(info);
            String s="{\"fish_id\":\""+array1.getJSONObject(0).getString("id")+"\"}";
            volleyrequest request=new volleyrequest(this,uid,"copyfishinfo",s);
            request.dogetfood();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//{"success" : 1, "result" : [{"id":1,"short_name":"Carp","detail_name":"carp","min_temp":4,"nitrogen_waste_a":0.19850000000000001,"nitrogen_waste_b":2.94050000000000011,"p_waste_a":0.00449999999999999966,"p_waste_b":0.00630000000000000004,"memo":null},{"id":12,"short_name":"TestFishName","detail_name":null,"min_temp":4,"nitrogen_waste_a":0.19850000000000001,"nitrogen_waste_b":2.94050000000000011,"p_waste_a":0.00449999999999999966,"p_waste_b":0.00630000000000000004,"memo":null},{"id":8,"short_name":"Whale","detail_name":"shark","min_temp":4,"nitrogen_waste_a":0.190000000000000002,"nitrogen_waste_b":2.94050000000000011,"p_waste_a":0.00449999999999999966,"p_waste_b":0.00630000000000000004,"memo":null},{"id":17,"short_name":"aaa","detail_name":"aaa","min_temp":0,"nitrogen_waste_a":0,"nitrogen_waste_b":0,"p_waste_a":0,"p_waste_b":0,"memo":null},{"id":19,"short_name":"TestFishName11","detail_name":null,"min_temp":4,"nitrogen_waste_a":0.19850000000000001,"nitrogen_waste_b":2.94050000000000011,"p_waste_a":0.00449999999999999966,"p_waste_b":0.00630000000000000004,"memo":null},{"id":20,"short_name":"myfishname","detail_name":null,"min_temp":4,"nitrogen_waste_a":0.19850000000000001,"nitrogen_waste_b":2.94050000000000011,"p_waste_a":0.00449999999999999966,"p_waste_b":0.00630000000000000004,"memo":null},{"id":22,"short_name":"saf","detail_name":null,"min_temp":4,"nitrogen_waste_a":0.19850000000000001,"nitrogen_waste_b":2.94050000000000011,"p_waste_a":0.00449999999999999966,"p_waste_b":0.00630000000000000004,"memo":null},{"id":21,"short_name":"fishA","detail_name":"fisha","min_temp":4,"nitrogen_waste_a":0.190000000000000002,"nitrogen_waste_b":2.94050000000000011,"p_waste_a":0.00449999999999999966,"p_waste_b":0.00630000000000000004,"memo":null}]}
    @Override
    public void getfeed(String feed) {
        if(feed==null){
            Toast.makeText(this,"没有数据，请检查是否每个阶段均有饲料", Toast.LENGTH_SHORT).show();
        }
        if(mode==0) {
            Intent intent = new Intent(PredictActivity.this, ChartActivity.class);
            intent.putExtra("info", feed);
            intent.putExtra("uid",uid);
            startActivity(intent);
        }else if(mode==1){
            Intent intent = new Intent(PredictActivity.this, FishFeedActivity.class);
            intent.putExtra("info",feed);
            intent.putExtra("uid",uid);
            startActivity(intent);
        }
        //{"start_day":0,"end_day":9,"day_type":2,"feed_req":"0.206,0.226,0.247,0.270,0.292,0.317,0.343,0.368,0.397,0.425",
        // "feed_rate":"2.943,2.824,2.722,2.642,2.543,2.465,2.396,2.320,2.259,2.192",
        // "feed_eff":"0.684,0.678,0.673,0.670,0.664,0.660,0.656,0.651,0.648,0.643",
        // "feed_digEnergy":"17.758,17.917,18.051,18.130,18.301,18.414,18.514,18.659,18.758,18.898"}
        else if(mode==3){
            Intent intent = new Intent(PredictActivity.this, FeedpreActivity.class);
            intent.putExtra("info", feed);
            intent.putExtra("uid",uid);
            startActivity(intent);
        }
    }
}
