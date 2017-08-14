package com.fishmanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import net.getfood;
import net.volleyrequest;

import implement.getfeedinterface;

public class MenuActivity extends AppCompatActivity implements getfeedinterface {
    private LayoutInflater minflater;
    private String uid;
    Intent intent;
    private classinfo[]classes={new classinfo("TGC计算",TGC1Activity.class),
    new classinfo("饲料管理",FoodManagerActivity.class),
    new classinfo("鱼类管理",FishActivity.class),
    new classinfo("预测分析",PredictActivity.class)};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        minflater=LayoutInflater.from(this);
        GridView gv=(GridView)findViewById(R.id.gridview);
        gv.setAdapter(new Myadapter());
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onitemclick(position);
            }
        });

    }

    @Override
    public void getfeed(String feed) {
//      if(feed==)
        intent.putExtra("info",feed);
        intent.putExtra("uid",uid);
        startActivity(intent);
    }

    public interface dosomething{
        public void addinfo();
    }
    private void onitemclick(int position){
        intent=new Intent(MenuActivity.this,classes[position].democlass);
        Intent in=getIntent();
        uid=in.getStringExtra("uid");
        intent.putExtra("uid",uid);
        Log.d("menu",Integer.toString(position));
        if(position==2){
//            String temp="[{\"fishName\":\"草鱼\",\"fishId\":1},{\"fishName\":\"鲫鱼\",\"fishId\":2}]";
//            intent.putExtra("fishset",temp);
            volleyrequest request=new volleyrequest(MenuActivity.this,uid,"getfishes","123");
            request.dogetfood();

        }
        if(position==1){
            getfood get_food=new getfood(this,uid);
            get_food.dogetfood();
        }
        if(position==3){
            volleyrequest request=new volleyrequest(MenuActivity.this,uid,"getfishes","123");
            request.dogetfood();
        }
        if(position==0){
            volleyrequest request=new volleyrequest(MenuActivity.this,uid,"getfishes","123");
            request.dogetfood();
        }
//        startActivity(intent);
    }
    protected class Myadapter extends BaseAdapter{


        @Override
        public int getCount() {
            return classes.length;
        }

        @Override
        public Object getItem(int position) {
            return classes[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
        if(convertView==null){
            convertView= minflater.inflate(R.layout.griditem,null);
            viewHolder=new ViewHolder();
            viewHolder.textView=(TextView)convertView.findViewById(R.id.textView3);
            convertView.setTag(viewHolder);
        }
            else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
            viewHolder.textView.setText(classes[position].classname);
            Activity ac=(Activity)convertView.getContext();
            Point p=new Point();
            ac.getWindowManager().getDefaultDisplay().getSize(p);
            viewHolder.textView.setHeight(p.y/2-50);
            return convertView;
        }
    }
    private class ViewHolder{
        private TextView textView;
    }
    private class classinfo{
        String classname;
        Class <? extends Activity>democlass;
        public classinfo(String name,Class democlass){
            this.classname=name;
            this.democlass=democlass;
        }
    }
}
