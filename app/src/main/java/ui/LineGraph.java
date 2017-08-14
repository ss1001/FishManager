package ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fishmanager.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 佘松 on 2017/7/19.
 */

public class LineGraph extends View {
    private int color;
    private Paint paint;
    private int[][] data;
    private int row;
    private int column;
    private float maxrow;
    private float maxcolumn;
    private int unitrow,unitrownum;
    private int unitcolumn,unitcolumnnum;
    private Point[] point;
    int width;
    int height;
    private final int LENGTH=100;
    private final int DISTANCE=25;
    private final int PART=10;
    public LineGraph(Context context) {
        super(context);
    }

    public LineGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.LineGraph);
        if(typedArray!=null){
            color=typedArray.getColor(R.styleable.LineGraph_color,Color.BLACK);
            paint=new Paint();
            paint.setColor(color);
            row=typedArray.getInteger(R.styleable.LineGraph_row,10);
            column=typedArray.getInteger(R.styleable.LineGraph_column,10);
            maxrow=10;
            maxcolumn=400;
            data=new int[row][2];
            point=new Point[10];
        }

    }

    public LineGraph(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    protected void onDraw(Canvas canvas){
        init();
        canvas.drawText("0",2*DISTANCE,height,paint);
        canvas.drawLine(LENGTH,height-2*DISTANCE,width-PART,height-2*DISTANCE,paint);
        canvas.drawLine(LENGTH,height-2*DISTANCE,LENGTH,PART,paint);
        canvas.drawLine(LENGTH,PART,LENGTH-DISTANCE,PART+DISTANCE,paint);
        canvas.drawLine(LENGTH,PART,LENGTH+DISTANCE,PART+DISTANCE,paint);
        canvas.drawLine(width-PART,height-2*DISTANCE,width-PART-DISTANCE,height-(LENGTH-DISTANCE),paint);
        canvas.drawLine(width-PART,height-50,width-35,height-25,paint);
        for(int i=0;i<row;i++) {
            canvas.drawText(Integer.toString(unitrownum*(i+1)),2*DISTANCE+unitrow*(i+1),height,paint);
        }
        for(int i=0;i<column;i++) {
            canvas.drawText(Integer.toString(unitcolumnnum*(i+1)),0,height-unitcolumn*(i+1),paint);
        }
        if(row==0)
            Log.d("servererror","无数据");
        for(int i=1;i<row;i++) {
            canvas.drawLine(point[i-1].x,point[i-1].y,point[i].x,point[i].y,paint);
        }
    }
    private void init(){
        for(int i=0;i<row;i++){
            point[i]=new Point(data[i][0],data[i][1]);
        }
        paint.setTextSize(50);
        paint.setStrokeWidth(10);
        width=getMeasuredWidth();
        height=getMeasuredHeight();
        unitrow=(width-150)/row;
        unitcolumn=(height-60)/column;
        unitrownum=(int)maxrow/row;
        unitcolumnnum=(int)maxcolumn/column;
    }
    public void loaddata(Map<Integer,Float> map){
        List<Float>list=new ArrayList<Float>();
        Iterator<Map.Entry<Integer,Float>> iterator=map.entrySet().iterator();
        Iterator<Map.Entry<Integer,Float>> it=map.entrySet().iterator();
        while(iterator.hasNext()){
        Map.Entry entry=iterator.next();
        list.add(Float.parseFloat((entry.getValue()).toString()));
        Collections.sort(list);
        maxcolumn=list.get(list.size()-1);
            maxrow=list.size();
            row=list.size();
            data=new int[row][2];
            for(int i=0;i<row;i++){
                Map.Entry entry_temp=it.next();
                data[i][0]=(int)entry_temp.getKey();
                data[i][1]=(int)entry_temp.getValue();
            }
}
    }
}
