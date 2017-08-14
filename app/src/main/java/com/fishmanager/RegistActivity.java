package com.fishmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.volleyrequest;

import implement.getfeedinterface;

public class RegistActivity extends AppCompatActivity implements getfeedinterface {
    private Button back,submit;
    private EditText name,psd,psd_1,tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        back=(Button)findViewById(R.id.back);
        submit=(Button)findViewById(R.id.regist_button);
        name=(EditText)findViewById(R.id.nameText);
        psd=(EditText)findViewById(R.id.passwordText);
        psd_1=(EditText)findViewById(R.id.editText);
        tel=(EditText)findViewById(R.id.editText2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistActivity.this.finish();
            }
        });
        submit=(Button)findViewById(R.id.btn_regist);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(psd.getText().toString()!=""&&name.getText().toString()!=""&&psd_1.getText().toString()!="") {
                    if (psd.getText().toString().equals(psd_1.getText().toString())) {
                        String param = "{\"email\":\"" + name.getText() + "\",\"password\":\"" + psd.getText() + "\",\"tel\":\"" + tel.getText() + "\"}";
                        volleyrequest request = new volleyrequest(RegistActivity.this, "", "registeruserinfo", param);
                        request.dogetfood();
                    }
                }
                else{
                    Toast.makeText(RegistActivity.this,"请输入完整",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void getfeed(String feed) {
        if(feed.equals("")){
            Toast.makeText(this,"注册失败,用户名已存在",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        }
    }
}
