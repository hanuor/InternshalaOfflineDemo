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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NetworkBReceiver extends BroadcastReceiver{
    SaveOfflineData saveOfflineData;
    Internal internal;
    @Override
    public void onReceive(Context context, Intent intent) {
        saveOfflineData = new SaveOfflineData(context);
        internal = new Internal(context);
        boolean status = NetworkUtil.getConnectivityStatusString(context);
        if(status){

            int _count = saveOfflineData.getCount();
            if(_count!=0){
                Log.d("Counnnnt", "" + _count);
                for(int i = 1; i<= _count ; i++){
                    ModelOfflineData modelOfflineData = saveOfflineData.queryDB("apply"+i);
                   if(modelOfflineData.getStat().equals("unprocessed")){
                        internal.start(modelOfflineData.getId(), modelOfflineData.getReg_url(),modelOfflineData.getReq_method(),modelOfflineData.getAnswer());
                    }
Log.d("Hey"," " + modelOfflineData.getStat());

                }

            }


        }
    }
}
