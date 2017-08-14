package com.fishmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import net.calculate_TGC;
import net.volleyrequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import adapter.food_adapter;
import adapter.spinnerAdapter;
import adapter.tgcAdapter;
import implement.getfeedinterface;

public class TGC1Activity extends AppCompatActivity implements getfeedinterface {
    private Button calculate,add,del;
    private EditText endWeight;
    private EditText temperature;
    private Spinner fish;
    String uid,info;
    tgcAdapter Myadapter;
    JSONArray array;
    String id;
    int seq,tem,wei;
    ArrayList<String> fishes,nums,tems,weis;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tgc1);
        initActionBar();
        init();
        Intent intent=getIntent();
        uid=intent.getStringExtra("uid");
        info=intent.getStringExtra("info");
        try {
            array=new JSONArray(info);
            id=array.getJSONObject(0).getString("id");
            for(int i=0;i<array.length();i++){
                String name=array.getJSONObject(i).getString("short_name");
                fishes.add(name);

            }
            spinnerAdapter fishadapter=new spinnerAdapter(this,fishes);
            fish.setAdapter(fishadapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void initActionBar(){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("back","press back");
                finish();
            }
        });

    }
    private void init(){
        calculate=(Button)findViewById(R.id.btn_cal1);
        endWeight=(EditText)findViewById(R.id.final_layout_weight);
        temperature=(EditText)findViewById(R.id.temperature);
        fish=(Spinner)findViewById(R.id.spinner2) ;
        add=(Button)findViewById(R.id.button6);
        del=(Button)findViewById(R.id.button7);
        lv=(ListView)findViewById(R.id.lv_info);
        fishes=new ArrayList<>();
        nums=new ArrayList<>();
        nums.add("天数");
        weis=new ArrayList<>();
        tems=new ArrayList<>();
        weis.add("平均重量");
        tems.add("平均温度");
        seq=1;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nums.add(Integer.toString(seq));
                seq++;
                weis.add(endWeight.getText().toString());
                tems.add(temperature.getText().toString());
                Myadapter=new tgcAdapter(TGC1Activity.this,nums,weis,tems);
                lv.setAdapter(Myadapter);
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nums.size()!=1){
                    nums.remove(1);
                    weis.remove(1);
                    tems.remove(1);
                }
                Myadapter=new tgcAdapter(TGC1Activity.this,nums,weis,tems);
                lv.setAdapter(Myadapter);
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num="",wei="",tem="";
                if(nums.size()==1){
                    Toast.makeText(TGC1Activity.this,"至少添加两行数据",Toast.LENGTH_SHORT);
                }
                for(int i=1;i<nums.size();i++){
                    num=num+nums.get(i)+",";
                    wei+=weis.get(i)+",";
                    tem+=tems.get(i)+",";
                }
                String Num=num.substring(0,num.length()-1);
                String Wei=wei.substring(0,wei.length()-1);
                String Tem=tem.substring(0,tem.length()-1);
                //{"fish_id":1,"time_unit":2,"day_seq_data":"1,2,3","body_wgt_data":"0.5,1.3,2.4","29,27,27"}
                String param="{\"fish_id\":"+id+",\"time_unit\":2,\"day_seq_data\":\""+Num+"\",\"body_wgt_data\":\""+Wei+"\",\"avg_temp_data\":\""+Tem+"\"}";
                volleyrequest request=new volleyrequest(TGC1Activity.this,uid,"addprivatehistoricaldata",param);
                request.dogetfood();
            }
        });
    }

    @Override
    public void getfeed(String feed) {

    }
}
