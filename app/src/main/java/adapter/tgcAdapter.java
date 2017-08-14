package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.fishmanager.R;

import java.util.ArrayList;

/**
 * Created by 佘松 on 2017/7/30.
 */

public class tgcAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> list,list1,list2;
    public tgcAdapter(Context context, ArrayList<String> list,ArrayList<String>list2,ArrayList<String>list3){
        this.context=context;
        this.list=list;
        this.list1=list2;
        this.list2=list3;
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
        tgcAdapter.ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder = new tgcAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.tgcitem, null);
            viewHolder.food_name = (TextView) convertView.findViewById(R.id.tv1);
            viewHolder.nums=(TextView)convertView.findViewById(R.id.tv2);
            viewHolder.weis=(TextView)convertView.findViewById(R.id.tv3);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(tgcAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.food_name.setText(list.get(position));
        viewHolder.nums.setText(list1.get(position));
        viewHolder.weis.setText(list2.get(position));
        return convertView;
    }
    private class ViewHolder{
        TextView food_name;
        TextView nums;
        TextView weis;
    }
}
