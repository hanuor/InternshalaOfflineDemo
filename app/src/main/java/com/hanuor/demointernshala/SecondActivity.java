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

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.hanuor.demointernshala.test.RetroClient;
import com.hanuor.demointernshala.test.RetroInterface;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class SecondActivity extends AppCompatActivity {
    TextView volley, retro;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        volley = (TextView) findViewById(R.id.volley);
        retro = (TextView) findViewById(R.id.retro);
       // Interface service = retrofit.create(Interface.class);
        RetroInterface servicer = RetroClient.getClient().create(RetroInterface.class);
        Call<JsonObject> req = servicer.getinternships("internships");
        req.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Response<JsonObject> response, Retrofit retrofit) {

                Log.e("Roar Retro", ""+response.body());
                retro.setTextColor(Color.parseColor("#eeeeee"));
                retro.setText(response.body().toString());

                StringRequest strReq = new StringRequest(Request.Method.GET,
                        "https://test.internshala.com/json/internships", new com.android.volley.Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("Roar  Volley   ", response.toString());
                        volley.setTextColor(Color.parseColor("#212121"));
                        volley.setText(response);
                        //  pDialog.hide();

                    }
                }, new com.android.volley.Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Roar  Volley  "," Error");
                        //  pDialog.hide();
                    }
                });


                RequestQueue requestQueue = Volley.newRequestQueue(SecondActivity.this);
                requestQueue.add(strReq);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Roar Retro", ""+t.getMessage());
            }
        });
// Tag used to cancel the request
        String  tag_string_req = "string_req";

        String url = "http://api.androidhive.info/volley/string_response.html";


    }
}
