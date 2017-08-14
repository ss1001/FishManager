package com.fishmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.food_adapter;

public class FoodManagerActivity extends AppCompatActivity {
    private Button btn_add;
    private Button btn_del;
    private ListView lv_food;
    private ArrayList<String>arrayList;
    private String foodName,uid;
    private food_adapter Myadapter;
    private Toolbar actionBar;
    JSONArray json;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_manager);
        Intent intent=getIntent();
        actionBar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodManagerActivity.this.finish();
            }
        });
        String s=intent.getStringExtra("info");
        btn_add=(Button)findViewById(R.id.btn_add);
        btn_del=(Button)findViewById(R.id.btn_del);
        lv_food=(ListView)findViewById(R.id.lv_foodset);
        arrayList=new ArrayList<String>();
        preferences= getSharedPreferences("feeds",0);
        //[{"id":1,"usr_id":2,"feed_name":"Yuehai1","dm":89,"cp":33,"cp_contri":23.6000000000000014,"cp_coef":0.839999999999999969,"lipid":2,"lipid_contri":39,"lipid_coef":
        // 0.900000000000000022,"cho":38,"cho_contri":17,"cho_coef":0.760000000000000009,"cf":8,"pi":1,"ash":16,"digestible_protein":0.770000000000000018,"digestible_ph":
        // 0.52300000000000002,"efw":3,"memo":null},{"id":2,"usr_id":2,"feed_name":"Yuehai2","dm":89,"cp":28,"cp_contri":23.6000000000000014,"cp_coef":0.819999999999999951,
        // "lipid":2.5,"lipid_contri":39,"lipid_coef":0.900000000000000022,"cho":38.5,"cho_contri":17,"cho_coef":0.760000000000000009,"cf":8,"pi":0.599999999999999978,"ash"
        // :12,"digestible_protein":0.819999999999999951,"digestible_ph":0.550000000000000044,"efw":3,"memo":null},{"id":3,"usr_id":2,"feed_name":"Yuehai3","dm":89,"cp":23,
        // "cp_contri":23.6000000000000014,"cp_coef":0.780000000000000027,"lipid":2.5,"lipid_contri":39,"lipid_coef":0.900000000000000022,"cho":39.5,"cho_contri":17,
        // "cho_coef":0.760000000000000009,
        // "cf":12,"pi":0.599999999999999978,"ash":12,"digestible_protein":0.780000000000000027,"digestible_ph":0.589999999999999969,"efw":3,"memo":null}]}
//        {"proteinD_ratio":25.41,"feedId":1,"feedName":"ss","crudeProtein":33,"fat":2,"ashContent":16,"phosphor":1,"dryMatter":89,"coraseFiber":8,"dissolveLoss_ratio":38,"phosphorD_ratio":0.52},\
//        {"proteinD_ratio":22.96,"feedId":2,"feedName":"aa","crudeProtein":28,"fat":2.5,"ashContent":12,"phosphor":0.6,"dryMatter":89,"coraseFiber":8,"dissolveLoss_ratio":38.5,"phosphorD_ratio":0.33},\
//        {"proteinD_ratio":17.94,"feedId":3,"feedName":"é¥²æc","crudeProtein":23,"fat":2.5,"ashContent":12,"phosphor":0.6,"dryMatter":89,"coraseFiber":12,"dissolveLoss_ratio":39.5,"phosphorD_ratio":0.35}\
//        ]

       // foodName="{\"proteinD_ratio\":25.41,\"feedId\":1,\"feedName\":\"ss\",\"crudeProtein\":33,\"fat\":2,\"ashContent\":16,\"phosphor\":1,\"dryMatter\":89,\"coraseFiber\":8,\"dissolveLoss_ratio\":38,\"phosphorD_ratio\":0.52}";
        try {
            json=new JSONArray(s);
            uid=getIntent().getStringExtra("uid");
            for(int i=0;i<json.length();i++) {
                arrayList.add( json.getJSONObject(i).getString("feed_name"));
            }
            Myadapter=new food_adapter(this,arrayList);
            lv_food.setAdapter(Myadapter);
            lv_food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  String temp= preferences.getString(arrayList.get(position),"no data");
                    String temp= null;
                    try {
                        temp = new String(json.getJSONObject(position).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(FoodManagerActivity.this,FoodinfoActivity.class);
                    intent.putExtra("feeds",temp);
                    intent.putExtra("uid",uid);
                    startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        btn_add=(Button)findViewById(R.id.btn_add);
        btn_del=(Button)findViewById(R.id.btn_del);

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FoodManagerActivity.this.finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FoodManagerActivity.this,FoodinfoActivity.class);
                intent.putExtra("uid",uid);
                startActivity(intent);
            }
        });
    }
}
