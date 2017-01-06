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

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Timer;

public class Servicer extends Service {
    Internal internal;
    SaveOfflineData offlineData;
    public static final long NOTIFY_INTERVAL = 1000; // 10 seconds
    public static final long NOTIFY_INTERVAL_FOR_TASK = 9000; // 10 seconds
    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    private Handler mHandler2 = new Handler();
    // timer handling
    private Timer mTimer = null;
    private Timer mTimer2 = null;
    GetSetGo getSetGo = new GetSetGo();
    ArrayList<Caller> callerArrayList;
    ArrayList<Caller> _caller;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

/*
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Nakedand","Dumbtss");
        offlineData = new SaveOfflineData(getApplicationContext());

        //_caller = offlineData.ForKey();
        offlineData.clearTable();
       // callerArrayList = offlineData.ForKey("apply");

        internal = new Internal(getApplicationContext());
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);

        if (mTimer2 != null) {
            mTimer2.cancel();
        } else {
            // recreate new
            mTimer2 = new Timer();
        }
        // schedule task
        mTimer2.scheduleAtFixedRate(new ScheduleTimerTask(), 0, NOTIFY_INTERVAL_FOR_TASK);

    }
    public void isActive(boolean value){
        if(value){
            getSetGo.setActive(true);
        }else{
            getSetGo.setActive(false);
        }
    }
    class TimeDisplayTimerTask extends TimerTask {
        @Override
        public void run() {
            // run on another thread

            mHandler.post(new Runnable() {

                @Override
                public void run() {

                    if(internal.isNetworkAvailable()){
                        Log.d("Nakedand","All the lights");
                        isActive(true);

                    }else{
                        isActive(false);
                    }

                                 }

            });
        }

    }
    class ScheduleTimerTask extends TimerTask {
        @Override
        public void run() {
            // run on another thread

            mHandler2.post(new Runnable() {

                @Override
                public void run() {
                        if(getSetGo.isActive()){
                            getSetGo.setFlag(true);
                            Log.d("All this ligg",""+_caller.size() + " but size   " + offlineData.getCount());
                            if(_caller.size()!=0) {
                                internal.start(_caller.get(0).getReg_url(), _caller.get(0).getReq_method(), _caller.get(0).getAnswer(),_caller.get(0).getMediator());
                                _caller.remove(0);
                            }
                            else{
                                _caller = offlineData.ForKey();
                                Log.d("All DONE","COME HEGDED");
                            }
                            }else{
                            if(getSetGo.isFlag()) {
                                Log.d("Heys", "" + offlineData.getCount());
                                offlineData.insertArrayList(_caller);
                                getSetGo.setFlag(false);
                            }
                        }

                }

            });
        }

    }
*/
}
