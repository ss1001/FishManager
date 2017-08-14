package com.fishmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.volleyrequest;

import org.json.JSONException;
import org.json.JSONObject;

import implement.getfeedinterface;

public class FoodinfoActivity extends AppCompatActivity implements getfeedinterface {
    private EditText name;
    private  EditText dm,cp,fat,cf,p,ac,pd,Pd,sr,cho;
    private Button bac,send;
    String uid,feedid;
    private Toolbar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodinfo);
        actionBar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodinfoActivity.this.finish();
            }
        });
        Intent intent=getIntent();
        String temp=intent.getStringExtra("feeds");
        init();
        uid=intent.getStringExtra("uid");

        //{"id":1,"usr_id":2,"feed_name":"Yuehai1","dm":89,"cp":33,"cp_contri":23.6,"cp_coef":0.84,"lipid":2,"lipid_contri":39,"lipid_coef":0.9,"cho":38,
        // "cho_contri":17,"cho_coef":0.76,"cf":8,"pi":1,"ash":16,"digestible_protein":0.77,"digestible_ph":0.523,"efw":3,"memo":null}
        if(temp!=null){
            //"proteinD_ratio":25.41,"feedId":1,"feedName":"ss","crudeProtein":33,"fat":2,"ashContent":16,
            // "phosphor":1,"dryMatter":89,"coraseFiber":8,"dissolveLoss_ratio":38,"phosphorD_ratio":0.52}
            try {
                JSONObject json=new JSONObject(temp);
                name.setText(json.get("feed_name").toString());
                dm.setText(json.get("dm").toString());
                cp.setText(json.get("cp").toString());
                fat.setText(json.get("lipid").toString());
                ac.setText(json.get("ash").toString());
                Pd.setText(json.get("digestible_ph").toString());
                pd.setText(json.get("digestible_protein").toString());
                p.setText(json.get("pi").toString());
                cf.setText(json.get("cf").toString());
                sr.setText(json.get("efw").toString());
                cho.setText(json.get("cho").toString());
                feedid=json.get("id").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
        }

    }
    void init(){
        name=(EditText)findViewById(R.id.food_name);
        dm=(EditText)findViewById(R.id.dm);
        cp=(EditText)findViewById(R.id.cp);
        fat=(EditText)findViewById(R.id.fat);
        ac=(EditText)findViewById(R.id.ac);
        Pd=(EditText)findViewById(R.id.Pd);
        pd=(EditText)findViewById(R.id.pd);
        p=(EditText)findViewById(R.id.p);
        cf=(EditText)findViewById(R.id.cf);
        sr=(EditText)findViewById(R.id.sr);
        cho=(EditText)findViewById(R.id.cho);
        bac=(Button)findViewById(R.id.button3);
        send=(Button)findViewById(R.id.button4);
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="{\"feed_id\":"+feedid+"}";
            volleyrequest request=new volleyrequest(FoodinfoActivity.this,uid,"deletefeed",s);
                request.dogetfood();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s="{\"feed_name\":\""+name.getText().toString()+"\",\"dm\":"+dm.getText().toString()+",\"cp\":"+cp.getText().toString()+",\"lipid\":"+fat.getText().toString()
                        +",\"cho\":"+cho.getText().toString()+",\"cf\":"+cf.getText().toString()+",\"pi\":"+p.getText().toString()+",\"ash\":"+ac.getText().toString()+
                        ",\"digestible_protein\":"+pd.getText().toString()+",\"digestible_ph\":"+Pd.getText().toString()+",\"efw\":"+sr.getText().toString()+"}";
                volleyrequest request=new volleyrequest(FoodinfoActivity.this,uid,"addfeed",s);
                request.dogetfood();
            }
        });
    }

    @Override
    public void getfeed(String feed) {
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
    }
}
