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
 * Created by 佘松 on 2017/7/20.
 */

public class pdischarge {
    private Context context;
    private String url="http://192.168.204.1:8084/test/TestServlet/testGetP";
    public pdischarge(Context context){
        this.context=context;
    }
    public void postmessage(){
        RequestQueue queue= Volley.newRequestQueue(context);
        StringRequest request=new StringRequest(Request.Method.POST,url,listener,errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("input","{\"usr_id\":2,\"fish_id\":1,\"init_wgt\":0.5,\"minimum_temp\":6,\"avg_temp_input\":25,\"end_day\":60, \"time_unit\":1}");
                return map;
            }
        };
        queue.add(request);
    }
    private Response.Listener<String> listener=new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {
            Log.d("pdischarge", s);
        }
    };
    private Response.ErrorListener errorListener=new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.d("pdischargeerror",volleyError.getMessage(),volleyError);
        }
    };
}
