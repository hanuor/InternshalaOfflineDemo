package com.hanuor.demointernshala.test;
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

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hanuor.demointernshala.repositories.CollegesRepository;
import com.hanuor.staticDb.AutoCompleteDatabase;
import com.hanuor.staticDb.AutoCompleteModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.hanuor.demointernshala.test.FragmentA.MY_PREFS_NAME;

public class SyncData extends Service {

    ArrayList<AutoCompleteModel> autoCompleteModels;
    String url = "https://test.internshala.com/json/student/get_autocomplete_data/college";
    String newUrl = "https://test.internshala.com/json/autocomplete/syncDataOnApp";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        final ArrayList<AutoCompleteModel> _arr = new ArrayList<AutoCompleteModel>();
        StringRequest strReq = new StringRequest(Request.Method.GET,
                newUrl, new com.android.volley.Response.Listener<String>() {

            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {

                Date fromDate = null;
                Date toDate = null;
                SharedPreferences prefs = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(new Date());

                String restoredText = prefs.getString("last_sync_on", null);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    fromDate = sdf.parse(restoredText);
                    toDate = sdf.parse(format);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (restoredText != null) {

                    String time = prefs.getString("last_sync_on", "null");//"null" is the default value.
                    Log.d("Timeisprecious","" + time);
                    Calendar c= Calendar.getInstance();
                    c.setTime(fromDate);
                    c.add(Calendar.DATE,7);
                    if(c.getTime().compareTo(toDate)<0){

                        SharedPreferences.Editor editorial = prefs.edit();
                        editorial.putString("last_sync_on", format);
                        editorial.commit();
                    }

                }else{
                    SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

                    editor.putString("last_sync_on", format);
                    editor.apply();
                    Log.d("Timeispreciousyo","" + format);
                }

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONObject jsonArray = jsonObject.getJSONObject("collegesData");
                    JSONArray jArray = jsonArray.getJSONArray("activeColleges");
                     for(int i = 0;i< jArray.length(); i++){
                        JSONObject jObj = jArray.getJSONObject(i);
                        autoCompleteModels.add(new AutoCompleteModel(i,Integer.valueOf(jObj.getString("id")), jObj.getString("name"), jObj.getString("status") ));
                    }
                    AutoCompleteDatabase aam = new AutoCompleteDatabase(getApplicationContext());
                    CollegesRepository collegesRepository = new CollegesRepository(getApplicationContext());
                    collegesRepository.storeData(autoCompleteModels);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Roar  Volley  "," Error");
                //  pDialog.hide();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(strReq);


    }
    public void updateTimeStamp(){

        Date fromDate = null;
        Date toDate = null;
        SharedPreferences prefs = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());

        String restoredText = prefs.getString("last_sync_on", null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fromDate = sdf.parse(restoredText);
            toDate = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (restoredText != null) {

            String time = prefs.getString("last_sync_on", "null");//"null" is the default value.
            Log.d("Timeisprecious","" + time);
            Calendar c= Calendar.getInstance();
            c.setTime(fromDate);
            c.add(Calendar.DATE,7);
            if(c.getTime().compareTo(toDate)<0){

                SharedPreferences.Editor editorial = prefs.edit();
                editorial.putString("last_sync_on", format);
                editorial.commit();
            }

        }else{
            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

            editor.putString("last_sync_on", format);
            editor.apply();

            Log.d("Timeispreciousyo","" + format);
        }
        restoredText = format;

        final String finalRestoredText = restoredText;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://test.internshala.com/json/autocomplete/syncDataOnApp",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("PostResponse","" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("PostResponse","" + error.toString());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("last_sync_on", finalRestoredText);
                return null;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);



    }

}
