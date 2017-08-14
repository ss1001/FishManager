package net;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 佘松 on 2017/7/23.
 */

public class calculate_TGC {
    int startWeight;
    int endWeight;
    int days;
    int temperature;
    int userID;
    Context context;
    private String url ="http://192.168.204.1:8084/test/TestServlet/testTGCs";
    public calculate_TGC(Context context,int userID,int startWeight,int endWeight,int days,int temperature){
        this.startWeight=startWeight;
        this.days=days;
        this.endWeight=endWeight;
        this.temperature=temperature;
        this.userID=userID;
        this.context=context;
    }
    public void sendMessage(){
        RequestQueue queue= Volley.newRequestQueue(context);
        StringRequest request=new StringRequest(Request.Method.POST,url,listener,errorlistoner){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("userID", Integer.toString(userID));
                map.put("endWeight",Integer.toString(endWeight));
                map.put("startWeight",Integer.toString(startWeight));
                map.put("days",Integer.toString(days));
                map.put("temp",Integer.toString(temperature));
                return map;
            }
        };
        queue.add(request);
    }
    private Response.Listener<String>listener=new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            Log.d("TGCcal",s);
        }
    };
    private Response.ErrorListener errorlistoner=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.d("TGCerror",volleyError.getMessage(),volleyError);
        }
    };

}
