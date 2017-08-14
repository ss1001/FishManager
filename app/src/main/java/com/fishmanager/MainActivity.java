package com.fishmanager;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler x=new Handler();
        x.postDelayed(new lancher(),1000);
    }
    class lancher implements Runnable{
        @Override
        public void run() {
            Intent launch=new Intent(MainActivity.this,loginActivity.class);
            startActivity(launch);
            MainActivity.this.finish();
        }
    }
}
