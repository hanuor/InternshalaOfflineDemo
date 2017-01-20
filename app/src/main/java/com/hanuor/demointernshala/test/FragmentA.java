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
import com.hanuor.staticDb.AutoCompleteDatabase;
import com.hanuor.staticDb.AutoCompleteModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentA extends android.support.v4.app.Fragment {

    Button begin;
    String url = "https://test.internshala.com/json/student/get_autocomplete_data/college";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.tabone, container, false);
        begin = (Button) v.findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ArrayList<AutoCompleteModel> _arr = new ArrayList<AutoCompleteModel>();
                StringRequest strReq = new StringRequest(Request.Method.GET,
                        url, new com.android.volley.Response.Listener<String>() {

                    @TargetApi(Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("colleges");
                            for(int i = 0;i< jsonArray.length(); i++){

                                _arr.add(new AutoCompleteModel(Integer.toString(i),Integer.toString(i),jsonArray.get(i).toString(),"status"));
                            }

                            AutoCompleteDatabase aam = new AutoCompleteDatabase(getContext());
                            aam.storeData(_arr);
                        } catch (JSONException e) {
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
