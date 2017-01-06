package com.hanuor.demointernshala;
/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Internal{
    SaveOfflineData offlineData;
    Context context;
    public Internal(Context ctx){
        this.context = ctx;
        offlineData = new SaveOfflineData(ctx);
    }
    public void start(final String _id, final String REGISTER_URL, final String method, final String answer){
        offlineData.updateStatus(_id,"processing");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Request Completed
                        offlineData.updateStatus(_id,"processed");
                        ArrayList<ModelOfflineData> modelOfflineDatas ;
                        modelOfflineDatas = offlineData.getAll();
                        for(int i = 0;i<  modelOfflineDatas.size(); i++){
                            Log.d("Data NOW","   "+ modelOfflineDatas.get(i).getId() + " and  status " + modelOfflineDatas.get(i).getStat());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        /*if(){

                        }else{

                        }*/
                        offlineData.updateStatus(_id,"unprocessed");

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username","username");
                params.put("pass","password");
                params.put("dumtss", "email");
                params.put("answer", answer);
                String json = null;
                try {
                    json = new ObjectMapper().writeValueAsString(params);
                  //  offlineData.storeData("apply",json,"",REGISTER_URL,"POST","","StringRequest","");
//                    Log.d("OfflineD",offlineData.queryDB("apply"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
