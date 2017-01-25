package com.hanuor.demointernshala.repositories;
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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hanuor.staticDb.AutoCompleteDatabase;
import com.hanuor.staticDb.AutoCompleteFields;
import com.hanuor.staticDb.AutoCompleteModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CollegesRepository extends AutoCompleteDatabase{
    private Context ctx;

    public CollegesRepository(Context context) {
        super(context);
        this.ctx = context;
    }

    public void storeData(ArrayList<AutoCompleteModel> _autoList){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("Calculation",":::");
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (AutoCompleteModel city : _autoList) {
                values.put(AutoCompleteFields.COLLEGES_ID, city.getId());
                values.put(AutoCompleteFields.COLLEGES_IDSERVER, city.getId_server());
                values.put(AutoCompleteFields.COLLEGES_NAME, city.getName());
                values.put(AutoCompleteFields.COLLEGES_STATUS, city.getStatus());
                db.insert(AutoCompleteFields.TABLE_COLLEGES, null, values);
            }
            db.setTransactionSuccessful();
            Log.d("Calculation","::::::");
            ArrayList<String> _get = getColleges();
            JSONArray jsonArray = new JSONArray(_get);
            HashMap<String, String> hMap = new HashMap<String, String>();
            hMap.put("collegesData",jsonArray.toString());
            JSONObject jsonObject = new JSONObject(hMap);

            Log.d("VamosHan","" + jsonObject.toString());
        } finally {
            db.endTransaction();
        }
    }
    public int queryDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "*" + " FROM " + AutoCompleteFields.TABLE_COLLEGES;
        Cursor cSor = db.rawQuery(query_params, null);
        return cSor.getCount();
    }

    public ArrayList<String> getCollegeNamesForAutoComplete(){
        ArrayList<String> _storeData = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query_params = "SELECT " + "* FROM " + AutoCompleteFields.TABLE_COLLEGES +";";
        Cursor cSor = db.rawQuery(query_params, null);
        if(cSor.moveToFirst()){
            do{
              _storeData.add(cSor.getString(cSor.getColumnIndex(AutoCompleteFields.COLLEGES_NAME)));

            }while(cSor.moveToNext());

        }else{
            return null;
        }
        return _storeData;
    }


}
