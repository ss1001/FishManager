package com.fishmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import implement.getfeedinterface;
import loginAndRegist.login_fish;


public class loginActivity extends AppCompatActivity implements getfeedinterface {
    private Button loginButton;
    private Button registButton;
    private EditText nameText;
    private EditText passwordText;
    private CheckBox isRemember;
    private SharedPreferences pref;
    private boolean isSelected=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    private void init(){
        loginButton=(Button)findViewById(R.id.btn_regist);
        registButton=(Button)findViewById(R.id.regist_button);
        nameText=(EditText) findViewById(R.id.nameText);
        passwordText=(EditText)findViewById(R.id.passwordText);
        isRemember=(CheckBox)findViewById(R.id.checkBox);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        nameText.setText(pref.getString("name",""));
        passwordText.setText(pref.getString("psd",""));
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected=isRemember.isChecked();
                if(isSelected){
                    SharedPreferences.Editor editor=pref.edit();
                    String name=nameText.getText().toString();
                    String psd=passwordText.getText().toString();
                    editor.putString("name",name);
                    editor.putString("psd",psd);
                    editor.commit();
                    Log.d("selected","checked");
                }
                else{
                    Log.d("selected","false");
                }
                Log.d("login","press");
                login_fish login=new login_fish(loginActivity.this,nameText.getText().toString(),passwordText.getText().toString());
                login.login();

            }
        });
        registButton=(Button)findViewById(R.id.regist_button);
        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginActivity.this,RegistActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getfeed(String feed) {
        Intent intent=new Intent(loginActivity.this,MenuActivity.class);
        intent.putExtra("uid",feed);
        startActivity(intent);
    }
}
