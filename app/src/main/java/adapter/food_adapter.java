package adapter;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fishmanager.R;

import java.util.ArrayList;

/**
 * Created by 佘松 on 2017/7/20.
 */

public class food_adapter extends BaseAdapter {
    private Context context;
    private ArrayList<String>list;
    public food_adapter(Context context, ArrayList<String> list){
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
            convertView = View.inflate(context, R.layout.fooditem, null);
            viewHolder.food_name = (TextView) convertView.findViewById(R.id.tv_food);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) convertView.getTag();
        }
        viewHolder.food_name.setText(list.get(position));
        return convertView;
    }
    private class ViewHolder{
        TextView food_name;
    }
}
