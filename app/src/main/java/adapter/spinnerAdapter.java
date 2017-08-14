package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fishmanager.R;

import java.util.ArrayList;

/**
 * Created by 佘松 on 2017/7/27.
 */

public class spinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> list;
    public spinnerAdapter(Context context, ArrayList<String> list){
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
        spinnerAdapter.ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder = new spinnerAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.spinnerlayout, null);
            viewHolder.food_name = (TextView) convertView.findViewById(R.id.textView7);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(spinnerAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.food_name.setText(list.get(position));
        return convertView;
    }
    private class ViewHolder{
        TextView food_name;
    }
}
