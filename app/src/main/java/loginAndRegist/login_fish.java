package loginAndRegist;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;




import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import implement.getfeedinterface;

/**
 * Created by 佘松 on 2017/7/14.
 */

public class login_fish {
    private String url = "http://47.93.237.6:8080/fishsystem/getData.jsp";
//private String url ="http://192.168.204.1:8084/test/TestServlet/testLogin";
    private Context context;
    public static String uid;
    private getfeedinterface terface;
    private String name,psd;
    public login_fish(Context context,String name,String psd) {
        this.context = context;
        this.terface=(getfeedinterface)context;
        this.name=name;
        this.psd=psd;
        Log.d("context", context.toString());
    }

    public void login() {
        RequestQueue mqueue = Volley.newRequestQueue(context);
//        JSONObject input=new JSONObject();
//        try {
//            input.put("input","{\"userNo\":test,\"password\":\"123456\"}");
//         //   input.put("123","456");
//            String temp=input.getString("input");
//            JSONObject te=  new JSONObject(temp);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,url,listenner,errorlistener){
            @Override
            public Map<String,String>getHeaders(){
                Map<String, String> map = new HashMap<String, String>();
                map.put("Content-Type", "application/x-www-form-urlencoded");
                return map;
            }
//
    @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>input=new HashMap<>();
                //input.put("input","{\"userNo\":\"test\",\"password\":\"123456\"}");
        input.put("fname","getuserinfo");
        String fparam="{\"email\":\""+name+"\",\"password\":\""+psd+"\"}";
        input.put("fparam",fparam);
                return input;
            }
        };
        mqueue.add(jsonObjectRequest);
    }
    protected Response.Listener<String> listenner= new Response.Listener<String>(

    ) {
        @Override
        public void onResponse(String s) {
            try {
                JSONObject json=new JSONObject(s);
                String b=json.getString("result");
                JSONObject temp=new JSONObject(b);
                uid=temp.getString("uid");
                terface.getfeed(uid);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("login",s);

        }
    };
    protected   Response.ErrorListener errorlistener= new Response.ErrorListener(

    ) {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            String s=volleyError.getMessage();
            Log.d("login",volleyError.getMessage(),volleyError);
        }
    };
}