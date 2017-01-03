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
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Servicer extends Service {
    Internal internal;
    SaveOfflineData offlineData;
    public static final long NOTIFY_INTERVAL = 1000; // 10 seconds

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;
    ArrayList<Caller> callerArrayList;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Nakedand","Dumbtss");
        offlineData = new SaveOfflineData(getApplicationContext());
        callerArrayList = offlineData.ForKey("apply");

        internal = new Internal(getApplicationContext());
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);

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
                        
                        internal.start();
                    }

                                 }

            });
        }

    }

}
