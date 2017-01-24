package com.hanuor.demointernshala.test;/*
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
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hanuor.demointernshala.R;
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

import static android.content.Context.MODE_PRIVATE;

public class FragmentA extends android.support.v4.app.Fragment {
    public static final String MY_PREFS_NAME = "AutocompleteSyncTimestamp";
    Button begin;
    ArrayList<AutoCompleteModel> autoCompleteModels;
    String url = "https://test.internshala.com/json/student/get_autocomplete_data/college";
    String newUrl = "https://test.internshala.com/json/autocomplete/syncDataOnApp";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.tabone, container, false);
        begin = (Button) v.findViewById(R.id.begin);
        autoCompleteModels = new ArrayList<AutoCompleteModel>();
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<AutoCompleteModel> _arr = new ArrayList<AutoCompleteModel>();
                StringRequest strReq = new StringRequest(Request.Method.GET,
                        newUrl, new com.android.volley.Response.Listener<String>() {

                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {

                        Date fromDate = null;
                        Date toDate = null;
                        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String format = simpleDateFormat.format(new Date());

                        String restoredText = prefs.getString("timestamp", null);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            fromDate = sdf.parse(restoredText);
                            toDate = sdf.parse(format);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                      if (restoredText != null) {

                          String time = prefs.getString("timestamp", "null");//"null" is the default value.
                          Log.d("Timeisprecious","" + time);
                          Calendar c= Calendar.getInstance();
                          c.setTime(fromDate);
                          c.add(Calendar.DATE,7);
                          if(c.getTime().compareTo(toDate)<0){

                              SharedPreferences.Editor editorial = prefs.edit();
                              editorial.putString("timestamp", format);
                              editorial.commit();
                          }

                        }else{
                            SharedPreferences.Editor editor = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

                            editor.putString("timestamp", format);
                            editor.apply();
                            Log.d("Timeispreciousyo","" + format);
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONObject jsonArray = jsonObject.getJSONObject("collegesData");
                            JSONArray jArray = jsonArray.getJSONArray("activeColleges");

                            Log.d("JsonData", "Cusiine  " + jArray.toString() + " Lenght  " + jArray.length());
                            for(int i = 0;i< jArray.length(); i++){
                                JSONObject jObj = jArray.getJSONObject(i);
                                Log.d("WonderW","" + jArray.length());
                                Log.d("Wonder","  " + jObj.get("name"));
                                autoCompleteModels.add(new AutoCompleteModel(i,Integer.valueOf(jObj.getString("id")), jObj.getString("name"), jObj.getString("status") ));
                              //  _arr.add(new AutoCompleteModel(Integer.toString(i),Integer.toString(i),jsonArray.get(i).toString(),"status"));
                            }
                            

                            AutoCompleteDatabase aam = new AutoCompleteDatabase(getContext());
                            CollegesRepository collegesRepository = new CollegesRepository(getContext());
                            collegesRepository.storeData(autoCompleteModels);

                        } catch (JSONException e) {
                            Log.d("Houston","" + e.toString());
                            e.printStackTrace();
                        }

                        //  pDialog.hide();

                    }
                }, new com.android.volley.Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Roar  Volley  "," Error");
                        //  pDialog.hide();
                    }
                });


                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(strReq);

            }
        });
                return v;
    }
}
