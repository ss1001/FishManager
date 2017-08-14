package net;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 佘松 on 2017/7/24.
 */

public class grow_predict {
    Context context;
    String fish_id;
    String weeks;
    String temp;
    String lowtemp;
    String begin;
     static String url="http://47.93.237.6:8080/fishsystem/getData.jsp";
    //fname=getweightprediction&fparam=
    public grow_predict(Context context,String fish_id,String weeks,String temp,String lowtemp,String begin){
        this.context=context;
        this.begin=begin;
        this.fish_id=fish_id;
        this.temp=temp;
        this.lowtemp=lowtemp;
        this.weeks=weeks;
    }
    public  void predict(){
        RequestQueue queue= Volley.newRequestQueue(context);
        JSONObject json=new JSONObject();
        try {
            json.put("fname","getweightprediction");
            json.put("fparam","{\"fish_id\":1,\"init_wgt\":0.5,\"minimum_temp\":6,\"avg_tem\":28,\"end_day\":28,\"time_unit\":2}");
//
        StringRequest request=new StringRequest(Request.Method.POST,url,listener,errorlistener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("fname","gettgcs");
                //map.put("fparam","{\"fish_id\":1,\"init_wgt\":0.5,\"minimum_temp\":6,\"avg_tem\":28,\"end_day\":28,\"time_unit\":2}");
                map.put("fparam","{\"user_id\":25,\"fish_id\":1}");
                return map;
            }
        };
            queue.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    Response.Listener<String>listener=new Response.Listener<String>() {
        @Override
        public void onResponse(String jsonObject) {
            Log.d("grow",jsonObject);
        }
    };
    Response.ErrorListener errorlistener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.d("grow","fail",volleyError);
        }
    };
}
