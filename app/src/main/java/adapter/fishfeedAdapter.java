package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.fishmanager.R;

import java.util.ArrayList;

/**
 * Created by 佘松 on 2017/7/27.
 */

public class fishfeedAdapter extends BaseAdapter {

    private ArrayList<String>arrayList;
    private Context context;
    private ArrayList<String>feeds;

    public fishfeedAdapter(Context context,ArrayList<String>arrayList,ArrayList<String>feeds){
        this.arrayList=arrayList;
        this.context=context;
        this.feeds=feeds;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder viewholder;
        if(convertView==null){
            viewholder=new Viewholder();
            convertView=View.inflate(context, R.layout.feedfishlayout,null);
            viewholder.spinner=(Spinner)convertView.findViewById(R.id.spinner2);
            viewholder.textView=(TextView)convertView.findViewById(R.id.textView6);
            convertView.setTag(viewholder);
        }
        else {
            viewholder = (Viewholder) convertView.getTag();
        }
            spinnerAdapter spinneradapter=new spinnerAdapter(context,feeds);
            viewholder.spinner.setAdapter(spinneradapter);
            viewholder.textView.setText(arrayList.get(position));
        return convertView;
    }
    private class Viewholder{
        public Spinner spinner;
        public TextView textView;
    }
}
