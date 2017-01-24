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

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hanuor.demointernshala.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class FragmentA extends android.support.v4.app.Fragment {




    public static final String MY_PREFS_NAME = "AutocompleteSyncTimestamp";
    Button begin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.tabone, container, false);
        begin = (Button) v.findViewById(R.id.begin);


        Date fromDate = null;
        Date toDate = null;
        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
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
            Calendar c= Calendar.getInstance();
            c.setTime(fromDate);
            c.add(Calendar.DATE,7);
            if(c.getTime().compareTo(toDate)<0){
                getContext().startService(new Intent(getContext(), SyncData.class));
            }

        }else{
            getContext().startService(new Intent(getContext(), SyncData.class));


        }


        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
                return v;
    }
}
