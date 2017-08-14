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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import implement.getfeedinterface;

/**
 * Created by 佘松 on 2017/7/25.
 */
//{"success" : 1, "result" : [{"id":1,"short_name":"Carp","detail_name":"carp","min_temp":4,"nitrogen_waste_a":0.19850000000000001,"nitrogen_waste_b":2.94050000000000011,"p_waste_a":0.00449999999999999966,"p_waste_b":0.00630000000000000004,"memo":null}
public class volleyrequest {
    private Context context;
    private String url;
    public String uid;
    public String feedinfo;
    public String fname,fparam;
    private getfeedinterface terface;
    public volleyrequest(Context context,String uid,String fname,String fparam){
        this.context=context;
        this.uid=uid;
        this.url="http://47.93.237.6:8080/fishsystem/getData.jsp";
        this.fname=fname;
        this.fparam=fparam;
        this.terface=(getfeedinterface)context;
    }
    public void dogetfood(){
        RequestQueue queue= Volley.newRequestQueue(context);
        StringRequest request=new StringRequest(Request.Method.POST,url,listener,errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("fname",fname);
                //
                map.put("fparam",fparam);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                map.put("uid",uid);
                return map;
            }
        };
        queue.add(request);
    }
    Response.Listener<String>listener=new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            Log.d("getfood",s);
            try {
                JSONObject json=new JSONObject(s);
                int result=json.getInt("success");
                if(result==1) {
      //              JSONArray jsonArray=new JSONArray(json.getString("result"));
                    feedinfo=json.getString("result");
                    terface.getfeed(feedinfo);
                }else{
                    terface.getfeed("");
                }
            } catch (JSONException e) {
                terface.getfeed("");
                e.printStackTrace();
            }

        }
    };
    Response.ErrorListener errorListener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.d("server","sever error");
        }
    };

}
