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

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkBReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
        LoggerUtil.d(CommonValuesUtil.generalLogs, "Network Status: "+ status);

        // Explicitly specify that NetworkIntentService will handle the intent.
        ComponentName comp = new ComponentName(context.getPackageName(),
                NetworkIntentService.class.getName());
        intent.putExtra("status", status);

        // Start the service, keeping the device awake while it is launching.
        context.startService((intent.setComponent(comp)));

        if(isOrderedBroadcast()) {
            setResultCode(Activity.RESULT_OK);
        }
    }
}
