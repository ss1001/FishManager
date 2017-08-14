package com.fishmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.pdischarge;

public class PdischargedActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdischarged);
        button=(Button)findViewById(R.id.calculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            pdischarge p=new pdischarge(PdischargedActivity.this);
                p.postmessage();
            }
        });
    }
}
