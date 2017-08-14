package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fishmanager.R;

import java.util.ArrayList;

import entry.growinfo;

/**
 * Created by 佘松 on 2017/7/21.
 */

public class fishAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<growinfo> list;
    public fishAdapter(Context context, ArrayList<growinfo> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.fishlayout, null);
            viewHolder.food_name = (TextView) convertView.findViewById(R.id.growname);
            viewHolder.content=(TextView)convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.food_name.setText(list.get(position).fishgrow);
        viewHolder.content.setText(list.get(position).content);
        return convertView;
    }
    private class ViewHolder{
        TextView food_name;
        TextView content;
    }

    }
